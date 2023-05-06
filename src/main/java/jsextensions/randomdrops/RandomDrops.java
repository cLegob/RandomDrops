package jsextensions.randomdrops;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class RandomDrops extends JavaPlugin implements @NotNull Listener {

    @Override
    public void onEnable() {
        getLogger().info("Loading Random Drops version " + getDescription().getVersion());
        getServer().getPluginManager().registerEvents(this, this);
        new DropManager(this);
    }

}
