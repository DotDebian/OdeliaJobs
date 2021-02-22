package space.debian.odeliajobs.utils.guis;

import org.bukkit.inventory.ItemStack;
import space.debian.odeliajobs.utils.guis.Action;

public class GUIElement {
    private Action action;
    private ItemStack item;
    private Integer slot;
    private String requiredPermission;
    private String negativePermission;

    public Action getAction() {
        return this.action;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public Integer getSlot() {
        return this.slot;
    }

    public String getRequiredPermission() {
        return this.requiredPermission;
    }

    public String getNegativePermission() {
        return this.negativePermission;
    }

    public GUIElement(Integer slot, ItemStack item, Action action) {
        this.slot = slot;
        this.item = item;
        this.action = action;
        this.requiredPermission = null;
    }

    public GUIElement(Integer slot, ItemStack item, Action action, String requiredPermission) {
        this.slot = slot;
        this.item = item;
        this.action = action;
        this.requiredPermission = requiredPermission;
    }

    public GUIElement(Integer slot, ItemStack item, Action action, String requiredPermission, String negativePermission) {
        this.slot = slot;
        this.item = item;
        this.action = action;
        this.requiredPermission = requiredPermission;
        this.negativePermission = negativePermission;
    }
}

