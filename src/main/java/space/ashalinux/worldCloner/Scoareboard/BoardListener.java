package space.ashalinux.worldCloner.Scoareboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BoardListener implements Listener {

    private final FastBoardManager manager;

    public BoardListener(FastBoardManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        manager.createBoard(event.getPlayer());
    }
}
