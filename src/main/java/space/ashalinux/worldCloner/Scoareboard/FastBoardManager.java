package space.ashalinux.worldCloner.Scoareboard;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import space.ashalinux.worldCloner.Miscs.Tags;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FastBoardManager {

    private final Map<UUID, FastBoard> boards = new HashMap<>();

    public FastBoardManager(Plugin plugin) {

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (FastBoard board : boards.values()) {
                updateBoard(board);
            }
        }, 0L, 1L);
    }

    public void createBoard(Player player) {
        FastBoard board = new FastBoard(player);
        board.updateTitle(Tags.title());
        boards.put(player.getUniqueId(), board);
        updateBoard(board);
    }

    private void updateBoard(FastBoard board) {
        Player player = board.getPlayer();
        if (player == null || !player.isOnline()) return;
        board.updateLines(
                "text",
                "",
                "",
                "",
                "",
                "",
                "",
                "X: " + String.format("%.2f",player.getLocation().getX()) + "  Y: " + String.format("%.2f",player.getLocation().getY()) + "  Z: " + String.format("%.2f",player.getLocation().getZ()),
                "",
                "",
                "",
                "",
                "",
                "",
                "text"
        );

    }
}
