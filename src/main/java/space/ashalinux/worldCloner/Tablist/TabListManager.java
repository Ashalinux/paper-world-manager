package space.ashalinux.worldCloner.Tablist;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import space.ashalinux.worldCloner.Miscs.Tags;

public class TabListManager {

    private final JavaPlugin plugin;

    public TabListManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private void updateTabList(Player player) {
        Component header = Component.text("")
                .append(Component.newline())
                .append(Component.text(Tags.title()))
                .append(Component.newline())
                .append(Component.text("\n§4❤ Leben: §c" + Math.round(player.getHealth())))
                .append(Component.newline())
                .append(Component.text("§4🔥 Hunger: §c" + player.getFoodLevel()))
                .append(Component.newline())
                .append(Component.text("\n§4⚓ TPS: §c" + String.format("%.2f", Bukkit.getServer().getTPS()[1])))
                .append(Component.newline())
                .append(Component.text("§4⌚ Uhrzeit: §c" + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"))))
                .append(Component.newline())
                .append(Component.text("§4⛈ RAM: §c" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "MB§8/§c" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB"))
                .append(Component.newline());

        Component footer = Component.text("")
                .append(Component.newline())
                .append(Component.text("§4Running on Paper"))
                .append(Component.newline());

        player.sendPlayerListHeaderAndFooter(header, footer);
    }
    public void start() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                updateTabList(player);
            }
        }, 0L, 1L);
    }
}