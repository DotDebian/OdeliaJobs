package space.debian.odeliajobs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.objects.UserProfile;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.utils.JobsTopPAPI;
import space.debian.odeliajobs.utils.storage.UserData;

public class JobsTop {
    private HashMap<JobType, ArrayList<UserProfile>> topData = new HashMap();
    private JobsTopPAPI jobsTopPAPI;

    public JobsTop(Main plugin) {
        for (JobType job : JobType.values()) {
            this.topData.put(job, new ArrayList());
        }
        this.jobsTopPAPI = new JobsTopPAPI(plugin, this);
        this.jobsTopPAPI.setActive(true);
        this.jobsTopPAPI.register();
    }

    public int jobLevelComparator(UserProfile user1, UserProfile user2, JobType jobType) {
        if (user1.getJobLevel(jobType).equals(user2.getJobLevel(jobType))) {
            return 0;
        }
        if (user1.getJobLevel(jobType) < user2.getJobLevel(jobType)) {
            return 1;
        }
        return -1;
    }

    public HashMap<JobType, ArrayList<UserProfile>> updateTopData() {
        for (JobType jobType : JobType.values()) {
            this.topData.put(jobType, (ArrayList)UserData.getProfileMap().values().stream().sorted((user1, user2) -> this.jobLevelComparator((UserProfile)user1, (UserProfile)user2, jobType)).limit(10L).collect(Collectors.toList()));
        }
        return this.topData;
    }

    public HashMap<JobType, ArrayList<UserProfile>> getTopData() {
        return this.topData;
    }

    public void setTopData(HashMap<JobType, ArrayList<UserProfile>> topData) {
        this.topData = topData;
    }

    public ArrayList<UserProfile> getJobTopData(JobType jobType) {
        return this.topData.get((Object)jobType);
    }

    public JobsTopPAPI getJobsTopPAPI() {
        return this.jobsTopPAPI;
    }
}

