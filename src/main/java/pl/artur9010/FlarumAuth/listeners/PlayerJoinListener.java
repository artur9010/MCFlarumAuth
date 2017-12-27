package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        FlarumAuthPlugin.cleanupPlayer(event.getPlayer().getName());
        if(!FlarumAuthPlugin.accountExist(event.getPlayer().getName())){
            event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', "&c&lNie posiadasz konta na uCraft.pl\n\n&fZarejestruj sie na stronie &bwww.ucraft.pl\n\n\n&7(podczas rejestracji podaj nick &c&nIDENTYCZNY&7 jak w grze)"));
        }

        //teleport to spawn to avoid unexcepted events
        Player player = event.getPlayer();
        Location spawn = new Location(player.getWorld(), player.getWorld().getSpawnLocation().getX(), player.getWorld().getSpawnLocation().getY(), player.getWorld().getSpawnLocation().getZ(), 90, 0);
        player.teleport(spawn);
    }
}
