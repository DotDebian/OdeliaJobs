package space.debian.odeliajobs.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import space.debian.odeliajobs.objects.Reward;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.utils.JobUtils;
import space.debian.odeliajobs.utils.storage.Config;

public class RewardUtils {
    public static void processReward(String playerName, JobType jobType, Integer level) {
        Bukkit.getPlayerExact((String)playerName).getWorld().playSound(Bukkit.getPlayerExact((String)playerName).getLocation(), Sound.NOTE_PLING, 10.0f, 1.0f);
        Bukkit.getPlayerExact((String)playerName).sendMessage("\u00a72\u00bb \u00a7aTu viens de passer Niveau \u00a72" + (level + 1) + "\u00a7a sur le \u00a72" + JobUtils.prettyJobName(jobType) + "\u00a7a !");
        if (!Config.rewards.containsKey((Object)jobType) || !Config.rewards.get((Object)jobType).containsKey(level)) {
            return;
        }
        Reward reward = Config.rewards.get((Object)jobType).get(level);
        switch (reward.getType()) {
            case CONSOLE_COMMAND: {
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), reward.getData().replaceAll("%player%", playerName));
                break;
            }
            case GIVE_PERMISSION: {
                RegisteredServiceProvider permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
                ((Permission)permissionProvider.getProvider()).playerAdd(Bukkit.getPlayerExact((String)playerName), reward.getData());
            }
        }
        if (reward.getMessage() != null) {
            Arrays.asList(reward.getMessage().split("\n")).forEach(v -> Bukkit.getPlayerExact((String)playerName).sendMessage("\u00a72\u00bb \u00a7a" + v));
        }
        RewardUtils.rewardLogger(playerName, level + 1, reward.getData());
    }

    private static void rewardLogger(String username, int newLevel, String data) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("plugins/ExoticJobs/reward_logs/");
            sb.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            FileWriter fw = new FileWriter(sb.append(".log").toString(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            sb = new StringBuilder();
            sb.append(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            sb.append(": ");
            sb.append(username);
            sb.append(" - ");
            sb.append(newLevel);
            sb.append(" - ");
            sb.append(data);
            sb.append('\n');
            bw.write(sb.toString());
            bw.close();
        }
        catch (IOException e) {
            Bukkit.getLogger().severe("The Jobs Reward Logger extension failed to log an order.");
        }
    }
}

