package net.goosix.game;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface Game{

    void win(Player player);
    void starting(Player player1, Player player2);
    void playerBlockBreak(Player player, Block block);
    void playerInteract(PlayerInteractEvent event);
    void playerDeath(Player player);
    void playerDamagePlayer(Player damaged, Player damager);
    void giveStartItems(Player player, ItemStack... itemStack);

}
