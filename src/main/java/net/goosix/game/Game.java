package net.goosix.game;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Game{

    void win(Player player);
    void starting(Player player1, Player player2);
    void playerBlockBreak(Player player, Block block);
    void playerDeath(Player player);
    void ending(Player player1, Player player2);
    void playerBlockPlace(Player player, Block block);
    List<ItemStack> getStartItems();
    default void giveStartItems(Player... players){
        for (Player player : players) {
            player.getInventory().clear();
            getStartItems().forEach(itemStack -> {
                player.getInventory().addItem(itemStack);
            });
        }
    }

}
