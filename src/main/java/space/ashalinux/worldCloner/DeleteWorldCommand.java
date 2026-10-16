package space.ashalinux.worldCloner;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteWorldCommand extends Command {

    private final JavaPlugin plugin;

    public DeleteWorldCommand(String name, JavaPlugin plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können ihre Welt löschen!");
            return true;
        }

        File playerWorldFolder = new File("player_worlds", player.getUniqueId().toString());

        if (!playerWorldFolder.exists()) {
            player.sendMessage("Du hast keine Welt zum Löschen!");
            return true;
        }

        World world = Bukkit.getWorld("player_worlds/" + player.getUniqueId());
        if (world != null) {
            for (Player playerInWorld : world.getPlayers()) {
                playerInWorld.teleport(Bukkit.getWorld("world").getSpawnLocation());
            }

            boolean success = Bukkit.unloadWorld(world, false);
        }

        try {
            deleteFolder(playerWorldFolder);
            player.sendMessage("Deine Welt wurde gelöscht!");
        } catch (IOException e) {
            player.sendMessage("Fehler beim Löschen deiner Welt!");
        }

        return true;
    }

    private void deleteFolder(File folder) throws IOException {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                deleteFolder(file);
            }
        }
        Files.delete(folder.toPath());
    }
}
