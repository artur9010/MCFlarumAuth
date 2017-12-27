package pl.artur9010.FlarumAuth.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class LoginTimeKickTask implements Runnable {

    private final JavaPlugin plugin;

    public LoginTimeKickTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!FlarumAuthPlugin.isLoggedIn(p.getName())){
                if(!FlarumAuthPlugin.loginTime.containsKey(p.getName().toLowerCase())){
                    FlarumAuthPlugin.loginTime.put(p.getName().toLowerCase(), 0);
                }else{
                    FlarumAuthPlugin.loginTime.put(p.getName().toLowerCase(), FlarumAuthPlugin.loginTime.get(p.getName().toLowerCase()) + 1);
                    if(FlarumAuthPlugin.loginTime.get(p.getName().toLowerCase()) > 45){
                        p.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "Uplynal czas logowania!\n\n\n" + ChatColor.RESET + "Jesli nie pamietasz swojego hasla\nzresetujesz je na stronie " + ChatColor.AQUA + "www.ucraft.pl");
                    }
                }
            }
        }
    }

}
