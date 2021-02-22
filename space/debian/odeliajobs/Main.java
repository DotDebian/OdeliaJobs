package space.debian.odeliajobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Timer;
import java.util.TimerTask;
import me.clip.placeholderapi.PlaceholderAPI;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import space.debian.odeliajobs.commands.Jobs;
import space.debian.odeliajobs.events.InteractionHandler;
import space.debian.odeliajobs.events.JoinHandler;
import space.debian.odeliajobs.objects.UserProfile;
import space.debian.odeliajobs.utils.JobsPAPI;
import space.debian.odeliajobs.utils.JobsTop;
import space.debian.odeliajobs.utils.storage.Config;
import space.debian.odeliajobs.utils.storage.Persist;
import space.debian.odeliajobs.utils.storage.UserData;

public class Main
extends JavaPlugin {
    public static final String NEUTRAL_PREFIX = "\u00a76\u00bb \u00a7e";
    public static final String SUCCESS_PREFIX = "\u00a72\u00bb \u00a7a";
    public static final String ERROR_PREFIX = "\u00a74\u00bb \u00a7c";
    private static Main instance;
    private Timer userdataTimer = new Timer();
    private UserData userdata;
    private Gson gson;
    private Persist persist;
    private Config config;
    private JobsTop jobsTop;
    private Timer jobsTopTimer = new Timer();
    private JobsPAPI jobsPAPI;

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        this.getDataFolder().mkdirs();
        this.gson = this.getGsonBuilder().create();
        this.persist = new Persist(this);
        this.config = new Config();
        this.config.load(this.persist);
        this.userdata = new UserData();
        this.userdata.load(this.persist);
        this.getServer().getPluginCommand("jobs").setExecutor((CommandExecutor)new Jobs(this));
        this.getServer().getPluginManager().registerEvents((Listener)new InteractionHandler(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new JoinHandler(this), (Plugin)this);
        this.userdataTimer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                Main.this.userdata.save(Main.this.getPersist());
            }
        }, 1000000L, 1800000L);
        Bukkit.getOnlinePlayers().forEach(v -> {
            if (!UserData.getProfileMap().containsKey(v.getName())) {
                UserData.getProfileMap().put(v.getName(), new UserProfile(v.getName()));
            }
        });
        this.jobsTop = new JobsTop(this);
        this.jobsTopTimer = new Timer();
        this.jobsTopTimer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                Main.this.jobsTop.updateTopData();
            }
        }, 0L, 900000L);
        this.jobsPAPI = new JobsPAPI();
        this.jobsPAPI.register();
        this.jobsPAPI.setActive(true);
        this.getLogger().info("ExoticJobs initialization done");
    }

    public void onDisable() {
        this.userdata.save(this.getPersist());
        this.userdataTimer.cancel();
        this.jobsTopTimer.cancel();
        this.jobsTop.getJobsTopPAPI().setActive(false);
        PlaceholderAPI.unregisterPlaceholderHook((String)this.jobsTop.getJobsTopPAPI().getIdentifier());
        this.jobsPAPI.setActive(false);
        PlaceholderAPI.unregisterPlaceholderHook((String)this.jobsPAPI.getIdentifier());
        this.getLogger().info("ExoticJobs shutdown done");
    }

    private GsonBuilder getGsonBuilder() {
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().excludeFieldsWithModifiers(new int[]{128, 64});
    }

    public Gson getGson() {
        return this.gson;
    }

    public PlayerPoints getPlayerPoints() {
        return (PlayerPoints)PlayerPoints.class.cast((Object)this.getServer().getPluginManager().getPlugin("PlayerPoints"));
    }

    public Persist getPersist() {
        return this.persist;
    }

    public Config getConfigFile() {
        return this.config;
    }

    public UserData getUserData() {
        return this.userdata;
    }

    public JobsTop getJobsTop() {
        return this.jobsTop;
    }
}

