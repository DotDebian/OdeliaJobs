package space.debian.odeliajobs.utils.guis.actions;

import org.bukkit.entity.Player;
import space.debian.odeliajobs.utils.guis.Action;

public class Close
implements Action {
    @Override
    public void handle(Player player) {
        player.closeInventory();
    }
}

