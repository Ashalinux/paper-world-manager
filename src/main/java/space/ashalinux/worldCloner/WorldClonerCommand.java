package space.ashalinux.worldCloner;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class WorldClonerCommand extends Command {

    public WorldClonerCommand(String name, JavaPlugin plugin) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diese Welt klonen!");
            return true;
        }

        File serverFolder = new File(".");
        File templateWorld = new File(serverFolder, "player_worlds/world_template");
        File playerWorldsFolder = new File(serverFolder, "player_worlds");
        File playerWorld = new File(playerWorldsFolder, player.getUniqueId().toString());

        if (playerWorld.exists()) {
            player.sendTitle("§cERROR!", "§4Du hast bereits eine Welt!", 10, 70, 20);
            player.sendMessage("§cDu hast bereits eine Welt! Nutze /homeworld, um dorthin zu gelangen.");

            Location loc = player.getLocation();
            for (double i = 0; i < 2 * Math.PI; i += Math.PI / 16) {
                double x = Math.cos(i);
                double z = Math.sin(i);

                loc.getWorld().spawnParticle(Particle.DUST, loc.clone().add(x, 1, z), 2, new Particle.DustOptions(Color.RED, 5));
            }

            player.playSound(player.getLocation(), Sound.BLOCK_METAL_HIT, 2f, 1.2f);

            return true;
        }

        player.sendMessage("Deine Welt wird gerade geklont...");

        try {
            copyFolder(templateWorld, playerWorld);
            File uidFile = new File(playerWorld, "uid.dat");
            boolean deleted = uidFile.delete();
        } catch (IOException e) {
            return true;
        }

        World world = Bukkit.createWorld(new WorldCreator("player_worlds/" + player.getUniqueId()));
        if (world != null) {
            player.teleport(world.getSpawnLocation());
            player.sendMessage("Deine Welt ist fertig und du wurdest teleportiert!");
        }

        return true;
    }

    private void copyFolder(File source, File target) throws IOException {
        if (!target.exists()) target.mkdirs();

        for (File file : Objects.requireNonNull(source.listFiles())) {
            File destination = new File(target, file.getName());
            if (file.isDirectory()) {
                copyFolder(file, destination);
            } else {
                Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
