package space.debian.odeliajobs.utils.storage;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import space.debian.odeliajobs.objects.EventReward;
import space.debian.odeliajobs.objects.Reward;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.objects.types.RewardType;
import space.debian.odeliajobs.utils.storage.Persist;
import space.debian.odeliajobs.utils.storage.Saver;

public class Config
implements Saver {
    public static ArrayList<Integer> levels = new ArrayList();
    public static HashMap<JobType, HashMap<Integer, Reward>> rewards = new HashMap();
    public static HashMap<JobType, HashMap<EntityType, EventReward>> entityDeathEvent = new HashMap();
    public static HashMap<JobType, HashMap<Material, EventReward>> blockBreakEvent = new HashMap();
    public static HashMap<JobType, HashMap<Material, EventReward>> farmBlockBreakEvent = new HashMap();
    public static HashMap<JobType, HashMap<Material, EventReward>> furnaceExtractEvent = new HashMap();
    public static HashMap<JobType, HashMap<Material, EventReward>> blockPlaceEvent = new HashMap();
    private static transient Config i = new Config();

    @Override
    public void save(Persist persist) {
        persist.save(i);
    }

    @Override
    public void load(Persist persist) {
        persist.loadOrSaveDefault(i, Config.class, this.getClass().getSimpleName().toLowerCase());
    }

    static {
        Integer levelLadderBase = 333;
        Integer levelLadder = 419;
        for (int i = 0; i <= 99; ++i) {
            levels.add(levelLadderBase + i * levelLadder);
        }
        rewards.put(JobType.MINER, new HashMap());
        rewards.get((Object)JobType.MINER).put(14, new Reward(RewardType.GIVE_PERMISSION, "jobs.access.miner", "Tu viens de d\u00e9bloquer l'acc\u00e8s au shop de \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(24, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.miner.pickaxe.auto", "Tu viens de d\u00e9bloquer l'achat de la pioche auto-cuisante de \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(34, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.miner.pickaxe.spawner", "Tu viens de d\u00e9bloquer l'achat de la pioche \u00e0 spawners de \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(49, new Reward(RewardType.GIVE_PERMISSION, "boosters.nightvision", "Tu viens de d\u00e9bloquer l'achat du booster nightvision de \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(74, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.miner.pickaxe.legendary", "Tu viens de d\u00e9bloquer l'achat de la pioche des l\u00e9gendes de \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(99, new Reward(RewardType.GIVE_PERMISSION, "boosters.doubleore", "Tu viens de d\u00e9bloquer le double minerais de \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(9, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(19, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(29, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(39, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(48, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(59, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(69, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(79, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(89, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.get((Object)JobType.MINER).put(98, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Mineur\u00a7a !"));
        rewards.put(JobType.HUNTER, new HashMap());
        rewards.get((Object)JobType.HUNTER).put(14, new Reward(RewardType.GIVE_PERMISSION, "jobs.access.hunter", "Tu viens de d\u00e9bloquer l'acc\u00e8s au shop de \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(24, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.hunter.sword.first", "Tu viens de d\u00e9bloquer l'achat de la premi\u00e8re \u00e9p\u00e9e de \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(49, new Reward(RewardType.GIVE_PERMISSION, "boosters.saturation", "Tu viens de d\u00e9bloquer l'achat du booster saturation de \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(74, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.hunter.sword.blackbeard", "Tu viens de d\u00e9bloquer l'achat de la lame de barbe-noire pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(99, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.hunter.sword.second", "Tu viens de d\u00e9bloquer l'achat de la seconde \u00e9p\u00e9e de \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(9, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(19, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(29, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(39, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(48, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(59, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(69, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(79, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(89, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.get((Object)JobType.HUNTER).put(98, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Chasseur\u00a7a !"));
        rewards.put(JobType.FARMER, new HashMap());
        rewards.get((Object)JobType.FARMER).put(14, new Reward(RewardType.GIVE_PERMISSION, "jobs.access.farmer", "Tu viens de d\u00e9bloquer l'acc\u00e8s au shop de \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(24, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.farmer.hoe.first", "Tu viens de d\u00e9bloquer l'achat de la premi\u00e8re Houe de \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(49, new Reward(RewardType.GIVE_PERMISSION, "boosters.saturation", "Tu viens de d\u00e9bloquer l'achat du booster saturation de \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(74, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.farmer.hoe.second", "Tu viens de d\u00e9bloquer l'achat de la seconde Houe de \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(99, new Reward(RewardType.GIVE_PERMISSION, "jobs.shop.farmer.hoe.third", "Tu viens de d\u00e9bloquer l'achat de la troisi\u00e8me Houe de \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(9, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(19, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(29, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(39, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(48, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(59, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(69, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(79, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(89, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        rewards.get((Object)JobType.FARMER).put(98, new Reward(RewardType.CONSOLE_COMMAND, "cr give to %player% caissemetiers 1", "Tu viens d'obtenir une cl\u00e9 de m\u00e9tier pour le \u00a72Fermier\u00a7a !"));
        entityDeathEvent.put(JobType.HUNTER, new HashMap());
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.CHICKEN, new EventReward(2, 7.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.COW, new EventReward(2, 7.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.PIG, new EventReward(2, 7.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.SHEEP, new EventReward(2, 7.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.WOLF, new EventReward(5, 11.25));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.CREEPER, new EventReward(5, 37.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.SKELETON, new EventReward(3, 25.0));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.SPIDER, new EventReward(3, 12.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.ZOMBIE, new EventReward(3, 12.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.BLAZE, new EventReward(3, 25.0));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.CAVE_SPIDER, new EventReward(3, 25.0));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.ENDERMAN, new EventReward(5, 37.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.GHAST, new EventReward(10, 12.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.IRON_GOLEM, new EventReward(10, 25.0));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.MUSHROOM_COW, new EventReward(3, 25.0));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.PIG_ZOMBIE, new EventReward(3, 37.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.SILVERFISH, new EventReward(5, 12.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.SNOWMAN, new EventReward(2, 12.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.SQUID, new EventReward(2, 7.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.RABBIT, new EventReward(2, 7.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.GUARDIAN, new EventReward(5, 12.5));
        entityDeathEvent.get((Object)JobType.HUNTER).put(EntityType.PLAYER, new EventReward(100, 500.0));
        blockBreakEvent.put(JobType.MINER, new HashMap());
        blockBreakEvent.get((Object)JobType.MINER).put(Material.STONE, new EventReward(1, 1.7));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.COAL_ORE, new EventReward(2, 7.5));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.REDSTONE_ORE, new EventReward(4, 8.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.IRON_ORE, new EventReward(4, 10.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.GOLD_ORE, new EventReward(3, 12.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.LAPIS_ORE, new EventReward(2, 12.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.DIAMOND_ORE, new EventReward(4, 20.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.EMERALD_ORE, new EventReward(10, 32.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.QUARTZ_ORE, new EventReward(10, 15.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.OBSIDIAN, new EventReward(10, 25.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.NETHERRACK, new EventReward(2, 3.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.NETHER_BRICK, new EventReward(2, 3.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.MOSSY_COBBLESTONE, new EventReward(3, 3.0));
        blockBreakEvent.get((Object)JobType.MINER).put(Material.PRISMARINE, new EventReward(3, 3.0));
        farmBlockBreakEvent.put(JobType.FARMER, new HashMap());
        farmBlockBreakEvent.get((Object)JobType.FARMER).put(Material.CROPS, new EventReward(4, 5.0));
        farmBlockBreakEvent.get((Object)JobType.FARMER).put(Material.CARROT, new EventReward(4, 5.0));
        farmBlockBreakEvent.get((Object)JobType.FARMER).put(Material.POTATO, new EventReward(4, 5.0));
        farmBlockBreakEvent.get((Object)JobType.FARMER).put(Material.NETHER_WARTS, new EventReward(3, 3.0));
        farmBlockBreakEvent.get((Object)JobType.FARMER).put(Material.COCOA, new EventReward(4, 3.0));
        furnaceExtractEvent.put(JobType.MINER, new HashMap());
        furnaceExtractEvent.get((Object)JobType.MINER).put(Material.IRON_INGOT, new EventReward(4, 0.0));
        furnaceExtractEvent.get((Object)JobType.MINER).put(Material.GOLD_INGOT, new EventReward(3, 0.0));
    }
}

