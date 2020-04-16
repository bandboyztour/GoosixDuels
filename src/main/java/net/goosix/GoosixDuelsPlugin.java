package net.goosix;

import net.goosix.database.GoosixDuelsDatabaseManager;
import net.goosix.database.SQLDatabase;
import net.goosix.database.SQLDatabaseImpl;
import net.goosix.game.GoosixDuelsStateStorage;
import net.goosix.listener.MainListener;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name = "GoosixDuels", version = "1.0")
@ApiVersion
@Author("Goosix")
public class GoosixDuelsPlugin extends JavaPlugin {

    private MainListener mainListener;
    private GoosixDuelsDatabaseManager db;
    private GoosixDuelsStateStorage stateStorage;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.mainListener = new MainListener(this);
        getServer().getPluginManager().registerEvents(this.mainListener, this);
        SQLDatabase sqlDatabase = new SQLDatabaseImpl(configuration.getString("database.url"),
                configuration.getString("database.databaseName"),
                configuration.getString("database.userName"),
                configuration.getString("database.userPassword"));
        this.db = new GoosixDuelsDatabaseManager(sqlDatabase);
        this.stateStorage = new GoosixDuelsStateStorage(this);
    }

    @Override
    public void onDisable() {

    }
}
