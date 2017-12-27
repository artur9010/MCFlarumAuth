package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class PlayerItemConsumeListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerConsumeItem(PlayerItemConsumeEvent event) {
        if (!FlarumAuthPlugin.isLoggedIn(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

}
