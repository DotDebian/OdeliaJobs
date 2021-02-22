package space.debian.odeliajobs.utils.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface ClickAction {
    public void handle(Player var1, ClickType var2);
}

