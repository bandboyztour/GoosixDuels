package net.goosix.menu.session;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface MenuSessionResolver {

    MenuSession getSessionByPlayer(Player player);

    Collection<MenuSession> getSessions();

    void inactiveSession(Player player);
}
