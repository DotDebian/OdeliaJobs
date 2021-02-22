package space.debian.odeliajobs.utils.guis.actions;

import org.bukkit.entity.Player;
import space.debian.odeliajobs.utils.guis.Action;

public class Message
implements Action {
    private final String messages;

    public Message(String messages) {
        this.messages = messages;
    }

    @Override
    public void handle(Player player) {
        for (String message : this.messages.split("\n")) {
            player.sendMessage(message);
        }
    }
}

