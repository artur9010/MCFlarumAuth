package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

/*
Copied from AuthMe code :c
 */

public class PlayerMoveListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {

        /*
         * Limit player X and Z movements to 1 block
         * Deny player Y+ movements (allows falling)
         */
        Location from = event.getFrom();
        Location to = event.getTo();
        if (from.getBlockX() == to.getBlockX()
                && from.getBlockZ() == to.getBlockZ()
                && from.getY() - to.getY() >= 0) {
            return;
        }

        Player player = event.getPlayer();
        if (FlarumAuthPlugin.isLoggedIn(player.getName())) {
            return;
        }

        Location spawn = new Location(player.getWorld(), player.getWorld().getSpawnLocation().getX(), player.getWorld().getSpawnLocation().getY(), player.getWorld().getSpawnLocation().getZ(), 90, 0);
        if (spawn.distance(player.getLocation()) > 2) {
            player.teleport(spawn);
        }
    }
}
