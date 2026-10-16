package space.ashalinux.worldCloner.Miscs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import space.ashalinux.worldCloner.GUI.WorldGUI;

public class SwapHandListener implements Listener {

    public SwapHandListener(JavaPlugin plugin) {
    }

    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
        WorldGUI.openWorldSelector(player);
    }
}
