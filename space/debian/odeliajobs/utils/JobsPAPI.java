package space.debian.odeliajobs.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.utils.storage.Config;
import space.debian.odeliajobs.utils.storage.UserData;

public class JobsPAPI
extends PlaceholderExpansion {
    private boolean active = true;

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return Main.getInstance().getDescription().getAuthors().toString();
    }

    public String getIdentifier() {
        return "jobs";
    }

    public String getVersion() {
        return Main.getInstance().getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        if (!this.active) {
            return null;
        }
        String[] identifiers = identifier.split("_");
        switch (identifiers[0]) {
            case "level": {
                return String.valueOf(UserData.getProfileMap().get(player.getName()).getJobLevel(JobType.valueOf(identifiers[1])) + 1);
            }
            case "currentxp": {
                return String.valueOf(UserData.getProfileMap().get(player.getName()).getXP(JobType.valueOf(identifiers[1])));
            }
            case "maxxp": {
                return String.valueOf(Config.levels.get(UserData.getProfileMap().get(player.getName()).getJobLevel(JobType.valueOf(identifiers[1]))));
            }
            case "percentage": {
                return String.valueOf(Math.round(100 * UserData.getProfileMap().get(player.getName()).getXP(JobType.valueOf(identifiers[1])) / Config.levels.get(UserData.getProfileMap().get(player.getName()).getJobLevel(JobType.valueOf(identifiers[1]))))) + '%';
            }
            case "progressbar": {
                StringBuilder sb = new StringBuilder();
                sb.append("\u00a7a");
                for (int i = 0; i < 10; ++i) {
                    if (i == Math.round(10.0f * (float)Math.round(100 * UserData.getProfileMap().get(player.getName()).getXP(JobType.valueOf(identifiers[1])) / Config.levels.get(UserData.getProfileMap().get(player.getName()).getJobLevel(JobType.valueOf(identifiers[1])))) / 100.0f)) {
                        sb.append("\u00a77");
                    }
                    sb.append("\u2b1b");
                }
                return sb.toString();
            }
        }
        return "";
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

