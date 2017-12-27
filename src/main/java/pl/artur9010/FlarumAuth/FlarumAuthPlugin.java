package pl.artur9010.FlarumAuth;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.json.JSONObject;
import pl.artur9010.FlarumAuth.commands.FlarumDevCommand;
import pl.artur9010.FlarumAuth.commands.LoginCommand;
import pl.artur9010.FlarumAuth.listeners.*;
import pl.artur9010.FlarumAuth.tasks.AnnoyingMessageTask;
import pl.artur9010.FlarumAuth.tasks.LoginTimeKickTask;

import java.util.ArrayList;
import java.util.HashMap;

public class FlarumAuthPlugin extends JavaPlugin {

    private static ArrayList<String> playersLoggedIn;
    private static ArrayList<String> pseudoCache;
    public static HashMap<String, Integer> loginTime;
    public static ArrayList<String> commandsAllowedWithoutLogin;

    private static PluginManager pluginManager;
    private static BukkitScheduler bukkitScheduler;

    public void onEnable(){
        //kick all players to avoid abuses
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("FlarumAuth restarted.");
        }

        playersLoggedIn = new ArrayList<>();
        pseudoCache = new ArrayList<>();
        loginTime = new HashMap<>();
        commandsAllowedWithoutLogin = new ArrayList<>();

        commandsAllowedWithoutLogin.add("/login");
        commandsAllowedWithoutLogin.add("/l");

        pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerLeaveListener(), this);
        pluginManager.registerEvents(new BlockListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new PlayerItemConsumeListener(), this);
        pluginManager.registerEvents(new PlayerInventoryListener(), this);
        pluginManager.registerEvents(new PlayerCommandListener(), this);
        pluginManager.registerEvents(new PlayerChatListener(), this);
        pluginManager.registerEvents(new PlayerItemHeldListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);

        bukkitScheduler = getServer().getScheduler();
        bukkitScheduler.scheduleSyncRepeatingTask(this, new LoginTimeKickTask(this), 20L, 20L);
        bukkitScheduler.scheduleSyncRepeatingTask(this, new AnnoyingMessageTask(this), 20L, 80L);

        getCommand("flarumdev").setExecutor(new FlarumDevCommand());
        getCommand("login").setExecutor(new LoginCommand());
    }

    public void onDisable(){
        //kick all players to avoid abuses
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("FlarumAuth restarted.");
        }
    }

    public static boolean logIn(String username, String password){
        username = username.toLowerCase();

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(Config.API_URL + "/api/token").header("accept", "application/json").header("Content-Type", "application/json").body("{\"identification\":\"" + username + "\", \"password\":\"" + password + "\"}").asJson();
            JSONObject jsonObject = new JSONObject(jsonResponse.getBody().toString());
            if(jsonObject.has("errors")){
                return false;
            }else if(jsonObject.has("token")){
                playersLoggedIn.add(username);
                if(loginTime.containsKey(username)){
                    loginTime.remove(username);
                }
                return true;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isLoggedIn(String username){
        username = username.toLowerCase();

        return playersLoggedIn.contains(username);
    }

    public static boolean accountExist(String username){
        username = username.toLowerCase();
        if(pseudoCache.contains(username)){
            return true;
        }
        //if user not in cache, do request
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(Config.API_URL + "/api/users/" + username).asJson();
            if(jsonResponse.getStatus() == 200){
                pseudoCache.add(username);
                return true;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void removeFromLoggedInList(String username){
        username = username.toLowerCase();
        if(playersLoggedIn.contains(username)){
            playersLoggedIn.remove(username);
        }
    }

    public static void cleanupPlayer(String username){
        username = username.toLowerCase();
        FlarumAuthPlugin.removeFromLoggedInList(username);
        if(FlarumAuthPlugin.loginTime.containsKey(username)){
            FlarumAuthPlugin.loginTime.remove(username);
        }
    }

    public static ArrayList getPlayersLoggedIn(){
        return playersLoggedIn;
    }

}
