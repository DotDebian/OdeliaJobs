package space.debian.odeliajobs.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.objects.UserProfile;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.utils.JobsTop;

public class JobsTopPAPI
extends PlaceholderExpansion {
    private JobsTop jobsTop;
    private Main plugin;
    private boolean active = true;

    public JobsTopPAPI(Main plugin, JobsTop jobsTop) {
        this.plugin = plugin;
        this.jobsTop = jobsTop;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }

    public String getIdentifier() {
        return "jobstop";
    }

    public String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        if (!this.active) {
            return null;
        }
        try {
            JobType.valueOf(identifier.split("_")[0]);
        }
        catch (Exception e) {
            return null;
        }
        JobType jobType = JobType.valueOf(identifier.split("_")[0]);
        Integer rank = Integer.parseInt(identifier.split("_")[1]) - 1;
        StringBuilder sb = new StringBuilder();
        sb.append("\u00a7e");
        sb.append(rank + 1);
        if (rank < this.plugin.getJobsTop().getJobTopData(jobType).size()) {
            UserProfile userProfile = this.plugin.getJobsTop().getJobTopData(jobType).get(rank);
            sb.append("# \u00a76");
            sb.append(userProfile.getPlayerName());
            sb.append(" \u00a7e- \u00a7eNiveau \u00a76");
            sb.append(userProfile.getJobLevel(jobType) + 1);
        } else {
            sb.append("# \u00a76... \u00a7e- \u00a76...");
        }
        return sb.toString();
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

