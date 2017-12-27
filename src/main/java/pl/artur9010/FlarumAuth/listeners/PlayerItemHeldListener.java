package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class PlayerItemHeldListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerHeldItem(PlayerItemHeldEvent event) {
        if (!FlarumAuthPlugin.isLoggedIn(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

}
