package space.ashalinux.worldCloner.GUI;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUICommand extends Command {

    public GUICommand(String name) {
        super(name);
        this.setDescription("Öffnet das World-GUI");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can open this menu!");
            return true;
        }

        WorldGUI.openWorldSelector(player);
        return true;
    }
}