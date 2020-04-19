package net.goosix.game;

import net.goosix.PlayerUtils;
import net.goosix.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StickGameImpl implements Game {

    private List<ItemStack> items;
    private Map<Player, List<Block>> placedBlocks;

    public StickGameImpl() {
        items = new ArrayList<>();
        placedBlocks = new HashMap<>();
        items.add(ItemBuilder.newBuilder()
                .material(Material.STICK)
                .amount(1)
                .withDisplayName("Палка-копалка")
                .addLineToLore("Чё смотришь, дерись давай")
                .addEnchant(Enchantment.KNOCKBACK, 1)
                .addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS)
                .build());
        items.add(ItemBuilder.newBuilder()
                .material(Material.WOOD_PICKAXE)
                .amount(1)
                .withDisplayName("Кирка")
                .addLineToLore("Лучше пойди блоки копать :/")
                .addEnchant(Enchantment.DIG_SPEED, 2)
                .addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS)
                .build());
        items.add(ItemBuilder.newBuilder()
                .material(Material.SANDSTONE)
                .amount(32)
                .withDisplayName("Камешки")
                .addLineToLore("Стройся давай :/")
                .addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS)
                .build());
    }

    @Override
    public void win(Player player) {
        // если есть кие то раунды не завершать игру
    }

    @Override
    public void starting(Player player1, Player player2) {
        giveStartItems(player1, player2);
        PlayerUtils.sendMessageToPlayers("§6StickDuels §8| §f Начинаем бой через", player1, player2);
    }

    @Override
    public void playerBlockBreak(Player player, Block block) {
        if (placedBlocks.get(player) != null && placedBlocks.containsKey(player) && placedBlocks.get(player).contains(block)) {
            placedBlocks.get(player).remove(block);
        }
    }

    @Override
    public void playerDeath(Player player) {
        // todo победа в раунде оппонента

    }

    @Override
    public void ending(Player player1, Player player2) {
        // конец игры
    }

    @Override
    public void playerBlockPlace(Player player, Block block) {
        if (!placedBlocks.containsKey(player)) {
            placedBlocks.put(player, new ArrayList<>());
        }
        placedBlocks.get(player).add(block);
    }


    @Override
    public List<ItemStack> getStartItems() {
        return items;
    }
}
