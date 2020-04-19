package net.goosix.menu.session;

import net.goosix.menu.item.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class SimpleMenuSession implements MenuSession {
    private String title;
    private Player owner;
    private Inventory inventory;
    private Map<Integer, MenuItem> items;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Map<Integer, MenuItem> getItems() {
        return Collections.unmodifiableMap(this.items);
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
