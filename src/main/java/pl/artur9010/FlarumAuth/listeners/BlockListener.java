package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class BlockListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!FlarumAuthPlugin.isLoggedIn(event.getPlayer().getName())){
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!FlarumAuthPlugin.isLoggedIn(event.getPlayer().getName())){
            event.setCancelled(true);
        }
    }

}
