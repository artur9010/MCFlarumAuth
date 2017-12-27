package pl.artur9010.FlarumAuth.commands;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.JSONObject;
import pl.artur9010.FlarumAuth.Config;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class FlarumDevCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if(args.length == 0){
                sender.sendMessage("no_arg");
                return true;
            }

            HttpResponse<JsonNode> jsonResponse = Unirest.post(Config.API_URL + "/api/token").header("accept", "application/json").header("Content-Type", "application/json").body("{\"identification\":\"artur9010\", \"password\":\"" + args[0] + "\"}").asJson();
            //sender.sendMessage(jsonResponse.getBody().toString());
            /*JSONObject jsonObject = new JSONObject(jsonResponse.getBody().toString());
            if(jsonObject.has("errors")){
                if(jsonObject.getJSONObject("errors").get("code").equals("resource_not_found")){
                    sender.sendMessage("Konto nie istnieje.");
                }
            }else{
                sender.sendMessage("Konto istnieje.");
            }*/
            sender.sendMessage("" + jsonResponse.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            if(sender instanceof Player){
                ((Player)sender).kickPlayer(ChatColor.RED + "Nie mozna polaczyc sie z serwerem logowania.");
            }
        }
        return true;
    }
}
