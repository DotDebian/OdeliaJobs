package space.debian.odeliajobs.utils.guis;

import java.util.ArrayList;
import java.util.HashMap;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.utils.guis.Action;
import space.debian.odeliajobs.utils.guis.GUIElement;

public class GUI {
    protected String name = "GUI";
    private Integer size = 54;
    public HashMap<Integer, GUIElement> elements = new HashMap();

    public GUI(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public GUI(String name) {
        this.name = name;
    }

    public void add_element(Integer slot, ItemStack item, Action action) {
        this.elements.remove(slot);
        this.elements.put(slot, new GUIElement(slot, item, action));
    }

    public void add_element(Integer slot, ItemStack item, Action action, String requiredPermission) {
        this.elements.remove(slot);
        this.elements.put(slot, new GUIElement(slot, item, action, requiredPermission));
    }

    public void add_element(Integer slot, ItemStack item, Action action, String requiredPermission, String negativePermission) {
        this.elements.remove(slot);
        this.elements.put(slot, new GUIElement(slot, item, action, requiredPermission, negativePermission));
    }

    public void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, (int)this.size, (String)this.name);
        this.elements.forEach((k, v) -> {
            ItemStack item = new ItemStack(v.getItem());
            ItemMeta meta = item.getItemMeta();
            if (meta.hasDisplayName()) {
                meta.setDisplayName(PlaceholderAPI.setPlaceholders((Player)player, (String)meta.getDisplayName()));
            }
            if (meta.hasLore()) {
                int i;
                ArrayList<String> lore = new ArrayList<String>(meta.getLore());
                for (i = 0; i < lore.size(); ++i) {
                    lore.set(i, PlaceholderAPI.setPlaceholders((Player)player, (String)((String)lore.get(i)).replaceAll("%gold%", Main.getInstance().getPlayerPoints().getAPI().look(player.getName()) + "")));
                }
                if (v.getRequiredPermission() != null && !player.hasPermission(v.getRequiredPermission())) {
                    for (i = 0; i < lore.size(); ++i) {
                        lore.set(i, ((String)lore.get(i)).replaceAll("%status%", "\u00a7cVerrouill\u00e9 \u00a74\u2716"));
                    }
                } else if (v.getNegativePermission() != null && !player.hasPermission(v.getNegativePermission())) {
                    for (i = 0; i < lore.size(); ++i) {
                        lore.set(i, ((String)lore.get(i)).replaceAll("%status%", "\u00a7eNon-achet\u00e9 \u00a76\u26c1"));
                    }
                } else if (v.getNegativePermission() != null && player.hasPermission(v.getNegativePermission())) {
                    for (i = 0; i < lore.size(); ++i) {
                        lore.set(i, ((String)lore.get(i)).replaceAll("%status%", "\u00a7aAchet\u00e9 \u00a72\u2714"));
                    }
                } else if (v.getRequiredPermission() != null && player.hasPermission(v.getRequiredPermission())) {
                    for (i = 0; i < lore.size(); ++i) {
                        lore.set(i, ((String)lore.get(i)).replaceAll("%status%", "\u00a7aD\u00e9bloqu\u00e9 \u00a72\u2714"));
                    }
                }
                meta.setLore(lore);
            }
            if (item.getType().equals((Object)Material.SKULL_ITEM)) {
                SkullMeta metaS = (SkullMeta)meta;
                metaS.setOwner(player.getName());
                item.setItemMeta((ItemMeta)metaS);
            } else {
                item.setItemMeta(meta);
            }
            if (v.getRequiredPermission() != null && !player.hasPermission(v.getRequiredPermission())) {
                item.setType(Material.STAINED_GLASS_PANE);
                item.setDurability((short)14);
            }
            inventory.setItem(v.getSlot().intValue(), item);
        });
        player.openInventory(inventory);
    }
}

