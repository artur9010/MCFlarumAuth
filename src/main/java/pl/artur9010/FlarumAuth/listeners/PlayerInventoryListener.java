package pl.artur9010.FlarumAuth.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import pl.artur9010.FlarumAuth.FlarumAuthPlugin;

public class PlayerInventoryListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        if (!FlarumAuthPlugin.isLoggedIn(event.getWhoClicked().getName())) {
            event.setCancelled(true);
        }
    }

}
