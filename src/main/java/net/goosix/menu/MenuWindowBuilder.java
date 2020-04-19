package net.goosix.menu;

import net.goosix.menu.session.MenuSessionResolver;
import net.goosix.menu.window.MenuWindow;
import net.goosix.menu.window.SimpleMenuWindow;

public class MenuWindowBuilder {
    private MenuSessionResolver sessionResolver;
    private String title;
    private int size;

    public MenuWindowBuilder(MenuSessionResolver sessionResolver) {
        this.sessionResolver = sessionResolver;
    }

    public MenuWindowBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MenuWindowBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public MenuWindow build() {
        return new SimpleMenuWindow(sessionResolver, title, size);
    }
}
