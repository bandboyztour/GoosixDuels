package net.goosix;

import lombok.Getter;
import net.goosix.listener.MainListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.bukkit.plugin.java.annotation.plugin.author.Authors;

@Plugin(name = "GoosixDuels", version = "1.0")
@ApiVersion
@Authors(value = {@Author("DKotov")})
public class GoosixDuelsPlugin extends JavaPlugin {

    private MainListener mainListener;
    @Getter
    private GoosixPlayers goosixPlayers;

    @Override
    public void onEnable() {
        mainListener = new MainListener(this);
        getServer().getPluginManager().registerEvents(mainListener, this);
        goosixPlayers = new GoosixPlayers();
    }

    @Override
    public void onDisable() {

    }
}
