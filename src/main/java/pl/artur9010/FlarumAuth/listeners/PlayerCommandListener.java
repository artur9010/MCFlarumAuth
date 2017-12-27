package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class PlayerCommandListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String cmd = event.getMessage().split(" ")[0].toLowerCase();
        if (FlarumAuthPlugin.commandsAllowedWithoutLogin.contains(cmd)) {
            return;
        }
        final Player player = event.getPlayer();
        if (!FlarumAuthPlugin.isLoggedIn(player.getName())) {
            event.setCancelled(true);
        }
    }

}
