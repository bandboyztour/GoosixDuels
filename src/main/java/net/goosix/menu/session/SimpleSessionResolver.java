package net.goosix.menu.session;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimpleSessionResolver implements MenuSessionResolver {

    private Map<Player, MenuSession> menuSession;

    public SimpleSessionResolver() {
        this.menuSession = new HashMap<>();
    }

    @Override
    public MenuSession getSessionByPlayer(Player player) {
        return menuSession.get(player);
    }

    @Override
    public Collection<MenuSession> getSessions() {
        return menuSession.values();
    }

    @Override
    public void inactiveSession(Player player) {
        this.menuSession.remove(player);
    }
}
