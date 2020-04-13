package net.goosix.listener;

import net.goosix.GoosixDuelsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    private GoosixDuelsPlugin main;

    public MainListener(GoosixDuelsPlugin goosixDuelsPlugin) {
        this.main = goosixDuelsPlugin;
    }

    @EventHandler
    public void onBlockBreak() {
        // если не в стик режиме отмена
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

    }
}
