package pl.artur9010.FlarumAuth.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class AnnoyingMessageTask implements Runnable {

    private final JavaPlugin plugin;

    public AnnoyingMessageTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!FlarumAuthPlugin.isLoggedIn(p.getName())){
                p.sendMessage("Zaloguj sie korzystajac z komendy " + ChatColor.AQUA + "/login <haslo>");
            }
        }
    }

}
