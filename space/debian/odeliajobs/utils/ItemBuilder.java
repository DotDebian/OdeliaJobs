package space.debian.odeliajobs.utils;

import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    public static ItemStack ItemCreator(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore.split("\n")));
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack ItemCreator(Material material, String name, String lore, HashMap<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore.split("\n")));
        if (enchants.containsKey((Object)Enchantment.LURE)) {
            itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }
        item.setItemMeta(itemMeta);
        enchants.forEach((arg_0, arg_1) -> ((ItemStack)item).addUnsafeEnchantment(arg_0, arg_1));
        return item;
    }

    public static ItemStack ItemCreator(Material material, String name, String lore, short dataID, HashMap<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(material, 1, dataID);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore.split("\n")));
        if (enchants.containsKey((Object)Enchantment.LURE)) {
            itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }
        item.setItemMeta(itemMeta);
        enchants.forEach((arg_0, arg_1) -> ((ItemStack)item).addUnsafeEnchantment(arg_0, arg_1));
        return item;
    }

    public static ItemStack ItemCreator(Material material, String name, String lore, short dataID) {
        ItemStack item = new ItemStack(material, 1, dataID);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore.split("\n")));
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack ItemCreator(Material material, String name, short dataID) {
        ItemStack item = new ItemStack(material, 1, dataID);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack ItemCreator(Material material, String name, short dataID, HashMap<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(material, 1, dataID);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        if (enchants.containsKey((Object)Enchantment.LURE)) {
            itemMeta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }
        item.setItemMeta(itemMeta);
        enchants.forEach((arg_0, arg_1) -> ((ItemStack)item).addUnsafeEnchantment(arg_0, arg_1));
        return item;
    }

    public static ItemStack ItemCreator(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }
}

