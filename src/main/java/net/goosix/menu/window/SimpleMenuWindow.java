package net.goosix.menu.window;

import net.goosix.menu.session.MenuSessionResolver;

public class SimpleMenuWindow implements MenuWindow {
    private MenuSessionResolver sessionResolver;
    private String title;
    private int size;

    public SimpleMenuWindow(MenuSessionResolver sessionResolver, String title, int size) {
        this.sessionResolver = sessionResolver;
        this.title = title;
        this.size = size;
    }


}
