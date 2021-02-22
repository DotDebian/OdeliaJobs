package space.debian.odeliajobs.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Misc {
    public static void giveOrDrop(ItemStack item, Player player) {
        if (player.getInventory().addItem(new ItemStack[]{item}).size() != 0) {
            player.getWorld().dropItem(player.getLocation(), item);
        }
    }
}

