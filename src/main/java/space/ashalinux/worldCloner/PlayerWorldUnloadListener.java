package space.ashalinux.worldCloner;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerWorldUnloadListener implements Listener {

    private final JavaPlugin plugin;

    public PlayerWorldUnloadListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String worldName = "player_worlds/" + event.getPlayer().getUniqueId();
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            for (Player playerInWorld : world.getPlayers()) {
                playerInWorld.teleport(Bukkit.getWorld("world").getSpawnLocation());
            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                boolean success = Bukkit.unloadWorld(world, false);
                plugin.getLogger().info("Entladen von " + worldName + ": " + success);
            }, 1L);
        }
    }
}
