package net.goosix.game;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface GameLogic {

    void starting(Player player);
    void playerQuit(Player player);
    void playerBlockBreak(Player player, Block block);
    void playerDeath(Player player);
    void playerDamagePlayer(Player damaged, Player damager);

}
