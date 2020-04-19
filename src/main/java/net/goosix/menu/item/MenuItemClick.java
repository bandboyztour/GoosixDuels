package net.goosix.menu.item;

import lombok.Getter;
import net.goosix.menu.session.MenuSession;

public class MenuItemClick {
    @Getter
    private final MenuSession menuSession;
    @Getter
    private final MenuItem clicked;
    @Getter
    private final int slot;

    public MenuItemClick(MenuSession menuSession, MenuItem clicked, int slot) {
        this.menuSession = menuSession;
        this.clicked = clicked;
        this.slot = slot;
    }

}