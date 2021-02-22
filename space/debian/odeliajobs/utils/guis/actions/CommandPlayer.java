package space.debian.odeliajobs.utils.guis.actions;

import org.bukkit.entity.Player;
import space.debian.odeliajobs.utils.guis.Action;

public class CommandPlayer
implements Action {
    private String commands;

    public CommandPlayer(String commands) {
        this.commands = commands;
    }

    @Override
    public void handle(Player player) {
        for (String command : this.commands.split("&&")) {
            player.performCommand(command.replace("%player%", player.getName()));
        }
    }
}

