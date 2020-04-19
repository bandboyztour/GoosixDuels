package net.goosix.menu.session;

import net.goosix.menu.item.MenuItem;
import org.bukkit.entity.Player;

import java.util.Map;

public interface MenuSession {
    String getTitle();

    Map<Integer, MenuItem> getItems();

    Player getOwner();

}
