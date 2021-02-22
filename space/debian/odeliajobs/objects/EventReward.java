package space.debian.odeliajobs.objects;

import org.bukkit.Bukkit;
import space.debian.odeliajobs.objects.UserProfile;
import space.debian.odeliajobs.objects.types.JobType;

public class EventReward {
    private Integer xpWon;
    private Double moneyWon;

    public EventReward(Integer xpWon, Double moneyWon) {
        this.xpWon = xpWon;
        this.moneyWon = moneyWon;
    }

    public Integer getXpWon() {
        return this.xpWon;
    }

    public Double getMoneyWon() {
        return this.moneyWon;
    }

    public void rewardPlayer(JobType jobType, UserProfile userProfile) {
        if (Bukkit.getPlayerExact((String)userProfile.getPlayerName()).hasPermission("boosters.jobs")) {
            userProfile.addJobXP(jobType, (int)((double)this.xpWon.intValue() * 1.5));
        } else {
            userProfile.addJobXP(jobType, this.xpWon);
        }
    }
}

