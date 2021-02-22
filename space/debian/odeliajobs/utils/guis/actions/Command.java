package space.debian.odeliajobs.utils.guis.actions;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.debian.odeliajobs.utils.guis.Action;

public class Command
implements Action {
    private String permissionNeeded;
    private String commands;

    public Command(String permissionNeeded, String commands) {
        this.permissionNeeded = permissionNeeded;
        this.commands = commands;
    }

    @Override
    public void handle(Player player) {
        if (!player.hasPermission(this.permissionNeeded)) {
            return;
        }
        for (String command : this.commands.split("&&")) {
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
        }
    }
}

