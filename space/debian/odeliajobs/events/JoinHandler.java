package space.debian.odeliajobs.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.objects.UserProfile;
import space.debian.odeliajobs.utils.storage.UserData;

public class JoinHandler
implements Listener {
    private Main plugin;

    public JoinHandler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!UserData.getProfileMap().containsKey(event.getPlayer().getName())) {
            UserData.getProfileMap().put(event.getPlayer().getName(), new UserProfile(event.getPlayer().getName()));
        }
    }
}

