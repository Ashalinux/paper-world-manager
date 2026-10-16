package space.ashalinux.worldCloner;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.ashalinux.worldCloner.Miscs.Tags;

import java.io.File;

public class HomeWorldCommand extends Command {

    public HomeWorldCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen!");
            return true;
        }

        String worldPath = "player_worlds/" + player.getUniqueId();
        File worldFolder = new File(Bukkit.getWorldContainer(), worldPath);

        if (!worldFolder.exists()) {
            player.sendMessage("§cDu hast noch keine eigene Welt! Nutze zuerst den Clone-Befehl.");
            return true;
        }

        player.sendMessage(Tags.prefix() + "§aTeleportiere zu deiner Welt...");

        World world = Bukkit.getWorld(worldPath);
        if (world == null) {
            world = Bukkit.createWorld(new WorldCreator(worldPath));
        }

        if (world != null) {
            player.teleport(world.getSpawnLocation());
            player.sendMessage("§aWillkommen zuhause!");
        } else {
            player.sendMessage("§cFehler beim Laden deiner Welt.");
        }

        return true;
    }
}