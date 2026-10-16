package space.ashalinux.worldCloner.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorldGUI implements Listener {
    public static void openWorldSelector(Player viewer) {
        Inventory inv = Bukkit.createInventory(null, 54, "Player Worlds");

        for (World world : Bukkit.getWorlds()) {
            if (world.getName().startsWith("player_worlds/")) {
                String uuidString = world.getName().replace("player_worlds/", "");

                try {
                    UUID ownerUUID = UUID.fromString(uuidString);
                    inv.addItem(getCreatorHead(ownerUUID, world.getName()));
                } catch (IllegalArgumentException e) {
                    //
                }
            }
        }

        viewer.openInventory(inv);
    }

    private static ItemStack getCreatorHead(UUID ownerUUID, String worldName) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        if (meta != null) {
            String ownerName = Bukkit.getOfflinePlayer(ownerUUID).getName();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(ownerUUID));
            meta.setDisplayName("§a" + (ownerName != null ? ownerName : "Unknown") + "'s Welt");

            List<String> lore = new ArrayList<>();
            lore.add("§7Klicke, um diese Welt zu besuchen.");
            lore.add("§8ID: " + worldName);
            meta.setLore(lore);

            head.setItemMeta(meta);
        }
        return head;
    }
}