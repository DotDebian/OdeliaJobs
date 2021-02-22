package space.debian.odeliajobs.utils;

import space.debian.odeliajobs.utils.storage.Config;

public class UserUtils {
    public static boolean canUserRankup(Integer currentLevel, Integer newXP) {
        return newXP >= Config.levels.get(currentLevel);
    }
}

