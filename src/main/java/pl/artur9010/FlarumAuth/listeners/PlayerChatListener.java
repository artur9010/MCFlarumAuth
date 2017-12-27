package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class PlayerChatListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        if (!FlarumAuthPlugin.isLoggedIn(player.getName())) {
            event.setCancelled(true);
        }
    }

}
