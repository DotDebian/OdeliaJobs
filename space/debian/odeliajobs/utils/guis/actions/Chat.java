package space.debian.odeliajobs.utils.guis.actions;

import org.bukkit.entity.Player;
import space.debian.odeliajobs.utils.guis.Action;

public class Chat
implements Action {
    private String messages;

    public Chat(String messages) {
        this.messages = messages;
    }

    @Override
    public void handle(Player player) {
        for (String message : this.messages.split("\n")) {
            player.chat(message);
        }
    }
}

