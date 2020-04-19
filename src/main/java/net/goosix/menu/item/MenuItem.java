package net.goosix.menu.item;

import java.util.function.Consumer;

public interface MenuItem {
    Consumer<MenuItemClick> getClickHandler();
}
