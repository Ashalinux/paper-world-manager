package space.ashalinux.worldCloner.Miscs;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.ZonedDateTime;

public class HappyHourService {

    private final JavaPlugin plugin;
    private final int hour;
    private final int minute;

    public HappyHourService(JavaPlugin plugin, int hour, int minute) {
        this.plugin = plugin;
        this.hour = hour;
        this.minute = minute;
    }

    public void start() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0);

        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusDays(1);
        }

        long delaySeconds = Duration.between(now, nextRun).getSeconds();
        long delayTicks = delaySeconds * 20;
        long repeatTicks = 20L * 60 * 60 * 24; // 24 Stunden

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            plugin.getLogger().info("Happy Hour! Es ist " + hour + ":" + String.format("%02d", minute));
        }, delayTicks, repeatTicks);
    }
}
