package space.debian.odeliajobs.utils.guis.actions;

import org.bukkit.entity.Player;
import space.debian.odeliajobs.utils.guis.Action;
import space.debian.odeliajobs.utils.guis.GUI;

public class OpenGui
implements Action {
    private String permissionNeeded = null;
    private GUI gui;

    public OpenGui(String permissionNeeded, GUI gui) {
        this.permissionNeeded = permissionNeeded;
        this.gui = gui;
    }

    public OpenGui(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void handle(Player player) {
        if (this.permissionNeeded != null && !player.hasPermission(this.permissionNeeded)) {
            return;
        }
        this.gui.openGUI(player);
    }
}

