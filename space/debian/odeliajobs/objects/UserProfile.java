package space.debian.odeliajobs.objects;

import java.util.HashMap;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.utils.RewardUtils;
import space.debian.odeliajobs.utils.UserUtils;
import space.debian.odeliajobs.utils.storage.Config;

public class UserProfile {
    private HashMap<JobType, Integer> jobsXP;
    private HashMap<JobType, Integer> jobsLevel;
    private String playerName;

    public UserProfile(String playerName) {
        this.playerName = playerName;
        this.jobsXP = new HashMap();
        this.jobsLevel = new HashMap();
        this.jobsXP.put(JobType.MINER, 0);
        this.jobsXP.put(JobType.FARMER, 0);
        this.jobsXP.put(JobType.HUNTER, 0);
        this.jobsLevel.put(JobType.MINER, 0);
        this.jobsLevel.put(JobType.FARMER, 0);
        this.jobsLevel.put(JobType.HUNTER, 0);
    }

    public void addJobXP(JobType jobType, Integer xp) {
        if (UserUtils.canUserRankup(this.getJobLevel(jobType), xp + this.jobsXP.getOrDefault((Object)jobType, 0))) {
            this.jobsXP.put(jobType, xp + this.jobsXP.getOrDefault((Object)jobType, 0) - Config.levels.get(this.getJobLevel(jobType)));
            this.jobsLevel.put(jobType, this.getJobLevel(jobType) + 1);
            RewardUtils.processReward(this.playerName, jobType, this.getJobLevel(jobType));
        } else {
            this.jobsXP.put(jobType, xp + this.jobsXP.getOrDefault((Object)jobType, 0));
        }
    }

    public Integer getJobLevel(JobType jobType) {
        return this.jobsLevel.getOrDefault((Object)jobType, 0);
    }

    public Integer getXP(JobType jobType) {
        return this.jobsXP.getOrDefault((Object)jobType, 0);
    }

    public void setXP(JobType jobType, Integer xp) {
        this.jobsXP.put(jobType, xp);
    }

    public void setLevel(JobType jobType, Integer level) {
        this.jobsLevel.put(jobType, level);
    }

    public String getPlayerName() {
        return this.playerName;
    }
}

