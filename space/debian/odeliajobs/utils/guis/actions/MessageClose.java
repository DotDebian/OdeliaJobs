package space.debian.odeliajobs.utils.guis.actions;

import org.bukkit.entity.Player;
import space.debian.odeliajobs.utils.guis.Action;

public class MessageClose
implements Action {
    private final String messages;

    public MessageClose(String messages) {
        this.messages = messages;
    }

    @Override
    public void handle(Player player) {
        player.closeInventory();
        for (String message : this.messages.split("\n")) {
            player.sendMessage(message);
        }
    }
}

