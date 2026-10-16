package space.ashalinux.worldCloner;

import org.bukkit.plugin.java.JavaPlugin;
import space.ashalinux.worldCloner.GUI.GUICommand;
import space.ashalinux.worldCloner.GUI.WorldGUI;
import space.ashalinux.worldCloner.GUI.WorldGUIListener;
import space.ashalinux.worldCloner.Miscs.HappyHourService;
import space.ashalinux.worldCloner.Miscs.SwapHandListener;
import space.ashalinux.worldCloner.Scoareboard.BoardListener;
import space.ashalinux.worldCloner.Scoareboard.FastBoardManager;
import space.ashalinux.worldCloner.Tablist.TabListManager;

public final class WorldCloner extends JavaPlugin {

    @Override
    public void onEnable() {
        WorldClonerCommand command = new WorldClonerCommand("cloneworld", this);
        this.getServer().getCommandMap().register("cloneworld", command);

        HomeWorldCommand homeCommand = new HomeWorldCommand("homeworld");
        this.getServer().getCommandMap().register("homeworld", homeCommand);

        DeleteWorldCommand deleteCommand = new DeleteWorldCommand("deleteworld", this);
        this.getServer().getCommandMap().register("deleteworld", deleteCommand);

        GUICommand worldCommand = new GUICommand("gui");
        this.getServer().getCommandMap().register("gui", worldCommand);

        HappyHourService service = new HappyHourService(this, 22, 0);
        service.start();

        TabListManager tabListManager = new TabListManager(this);
        tabListManager.start();

        FastBoardManager boardManager = new FastBoardManager(this);

        getServer().getPluginManager().registerEvents(new BoardListener(boardManager), this);

        getServer().getPluginManager().registerEvents(new PlayerWorldUnloadListener(this), this);
        getServer().getPluginManager().registerEvents(new SwapHandListener(this), this);
        getServer().getPluginManager().registerEvents(new WorldGUIListener(), this);
        getServer().getPluginManager().registerEvents(new WorldGUI(), this);
    }

    @Override
    public void onDisable() {
    }
}
