package space.debian.odeliajobs.utils;

import space.debian.odeliajobs.objects.types.JobType;

public class JobUtils {
    public static String prettyJobName(JobType jobType) {
        switch (jobType) {
            case FARMER: {
                return "Fermier";
            }
            case HUNTER: {
                return "Chasseur";
            }
            case MINER: {
                return "Mineur";
            }
            case LUMBERJACK: {
                return "B\u00fbcheron";
            }
        }
        return "Inconnu";
    }
}

