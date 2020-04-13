package net.goosix.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack itemStack;

    public static ItemBuilder newBuilder() {
        return new ItemBuilder();
    }

    public ItemBuilder material(Material material) {
        itemStack = new ItemStack(material);
        return this;
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder withDisplayName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder addUnsafeEnchant(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addLineToLore(String string) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.getLore() == null) {
            List<String> lore = new ArrayList<>();
            lore.add(string);
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        } else {
            List<String> lore = itemMeta.getLore();
            lore.add(string);
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemStack build() {
        if (itemStack == null) {
            return material(Material.AIR).build();
        }
        return itemStack;
    }
}
