package space.ashalinux.worldCloner.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class WorldGUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Player Worlds")) return;

        event.setCancelled(true); // Prevent players from taking the heads

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() != Material.PLAYER_HEAD) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        // Extract the world name from the lore (hidden in the ID line)
        if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasLore()) {
            String worldPath = clickedItem.getItemMeta().getLore().get(1).replace("§8ID: ", "");
            World targetWorld = Bukkit.getWorld(worldPath);

            if (targetWorld != null) {
                player.teleport(targetWorld.getSpawnLocation());
                player.sendMessage("§aDu wurdest zur Welt von " + clickedItem.getItemMeta().getDisplayName() + " §ateleportiert!");
                player.closeInventory();
            }
        }
    }
}