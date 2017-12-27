package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        FlarumAuthPlugin.cleanupPlayer(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event){
        FlarumAuthPlugin.cleanupPlayer(event.getPlayer().getName());
    }
}
