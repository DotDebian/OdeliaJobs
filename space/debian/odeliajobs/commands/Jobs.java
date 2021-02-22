package space.debian.odeliajobs.commands;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.utils.ItemBuilder;
import space.debian.odeliajobs.utils.JobUtils;
import space.debian.odeliajobs.utils.guis.GUI;
import space.debian.odeliajobs.utils.guis.actions.Chat;
import space.debian.odeliajobs.utils.guis.actions.None;

public class Jobs
implements CommandExecutor {
    private Main plugin;
    private GUI jobsGui;
    private static Jobs instance;

    public GUI getJobsGui() {
        return this.jobsGui;
    }

    public Jobs(Main plugin) {
        int i;
        instance = this;
        this.plugin = plugin;
        this.jobsGui = new GUI("\u00a76\u00a7lMENU \u00a78- \u00a7eJobs", 54);
        ItemStack greenGlassPane = ItemBuilder.ItemCreator(Material.STAINED_GLASS_PANE, "\u00a76", (short)4);
        None voidAction = new None();
        for (i = 0; i < 9; ++i) {
            this.jobsGui.add_element(i, greenGlassPane, voidAction);
        }
        this.jobsGui.add_element(9, greenGlassPane, voidAction);
        this.jobsGui.add_element(17, greenGlassPane, voidAction);
        for (i = 45; i < 54; ++i) {
            this.jobsGui.add_element(i, greenGlassPane, voidAction);
        }
        this.jobsGui.add_element(36, greenGlassPane, voidAction);
        this.jobsGui.add_element(44, greenGlassPane, voidAction);
        this.jobsGui.add_element(4, ItemBuilder.ItemCreator(Material.SKULL_ITEM, "\u00a7f\u00a7l\u279c \u00a76\u00a7lProfil", "\u00a78\n &7Compte: &b&l%player_name%\n &7Argent: &e%vault_eco_balance_formatted%$\n &7Cr\u00e9dits: \u00a7e%gold%\n\u00a78\n&6&l\u00bb &eClique ici pour &6cr\u00e9diter &eton\n&ecompte de &a&lcredits&e.", (short)SkullType.PLAYER.ordinal()), voidAction);
        this.jobsGui.add_element(20, ItemBuilder.ItemCreator(Material.LOG, "\u00a7f\u00a7l\u279c &6&lB\u00fbcheron", "&8\n\u00a77\u27a5 \u00a7eNiveau \u00a76%jobs_level_LUMBERJACK%\n   \u00a78\u00a7a%jobs_percentage_LUMBERJACK% \u00a78[%jobs_progressbar_LUMBERJACK%\u00a78] \u00a77(%jobs_currentxp_LUMBERJACK%\u00a78/\u00a77%jobs_maxxp_LUMBERJACK% XP)\n\u00a78"), voidAction);
        this.jobsGui.add_element(21, ItemBuilder.ItemCreator(Material.BOW, "\u00a7f\u00a7l\u279c &c&lChasseur", "&8\n\u00a77\u27a5 \u00a7eNiveau \u00a76%jobs_level_HUNTER%\n   \u00a78\u00a7a%jobs_percentage_HUNTER% \u00a78[%jobs_progressbar_HUNTER%\u00a78] \u00a77(%jobs_currentxp_HUNTER%\u00a78/\u00a77%jobs_maxxp_HUNTER% XP)\n\u00a78"), voidAction);
        this.jobsGui.add_element(23, ItemBuilder.ItemCreator(Material.IRON_ORE, "\u00a7f\u00a7l\u279c &b&lMineur", "&8\n\u00a77\u27a5 \u00a7eNiveau \u00a76%jobs_level_MINER%\n   \u00a78\u00a7a%jobs_percentage_MINER% \u00a78[%jobs_progressbar_MINER%\u00a78] \u00a77(%jobs_currentxp_MINER%\u00a78/\u00a77%jobs_maxxp_MINER% XP)\n\u00a78"), voidAction);
        this.jobsGui.add_element(24, ItemBuilder.ItemCreator(Material.WHEAT, "\u00a7f\u00a7l\u279c &a&lFermier", "&8\n\u00a77\u27a5 \u00a7eNiveau \u00a76%jobs_level_FARMER%\n   \u00a78\u00a7a%jobs_percentage_FARMER% \u00a78[%jobs_progressbar_FARMER%\u00a78] \u00a77(%jobs_currentxp_FARMER%\u00a78/\u00a77%jobs_maxxp_FARMER% XP)\n\u00a78"), voidAction);
        this.jobsGui.add_element(29, ItemBuilder.ItemCreator(Material.BOOK_AND_QUILL, "\u00a7f\u00a7l\u279c &e&lT\u00e2ches &8- &6&lB\u00fbcheron", "&8\n\u00a77\u27a5 \u00a76Miner:\n \u00a77 - \u00a7eB\u00fbche: \u00a762 XP\n&8", new HashMap<Enchantment, Integer>(){
            {
                this.put(Enchantment.LURE, 1);
            }
        }), voidAction);
        this.jobsGui.add_element(30, ItemBuilder.ItemCreator(Material.BOOK_AND_QUILL, "\u00a7f\u00a7l\u279c &e&lT\u00e2ches &8- &c&lChasseur", "&8\n\u00a77\u27a5 \u00a76Tuer:\n \u00a77 - \u00a7ePoulet: \u00a762 XP \n \u00a77 - \u00a7eVache: \u00a762 XP \n \u00a77 - \u00a7eCochon: \u00a762 XP \n \u00a77 - \u00a7eMouton: \u00a762 XP \n \u00a77 - \u00a7ePoulpe: \u00a762 XP \n \u00a77 - \u00a7eLapin: \u00a762 XP\n \u00a77 - \u00a7eSquelette: \u00a763 XP \n \u00a77 - \u00a7eAraign\u00e9e: \u00a763 XP \n \u00a77 - \u00a7eZombie: \u00a763 XP \n \u00a77 - \u00a7eBlaze: \u00a763 XP  \n \u00a77 - \u00a7ePigZombie: \u00a763 XP\n \u00a77 - \u00a7eLoup: \u00a765 XP \n \u00a77 - \u00a7eCreeper: \u00a765 XP \n \u00a77 - \u00a7eEnderman: \u00a765 XP \n \u00a77 - \u00a7eSilverfish: \u00a765 XP\n \u00a77 - \u00a7eGolem: \u00a7610 XP\n \u00a77 - \u00a7eJoueur: \u00a76100 XP\n&8", new HashMap<Enchantment, Integer>(){
            {
                this.put(Enchantment.LURE, 1);
            }
        }), voidAction);
        this.jobsGui.add_element(32, ItemBuilder.ItemCreator(Material.BOOK_AND_QUILL, "\u00a7f\u00a7l\u279c &e&lT\u00e2ches &8- &b&lMineur", "&8\n\u00a77\u27a5 \u00a76Miner:\n \u00a77 - \u00a7eStone: \u00a761 XP\n \u00a77 - \u00a7eCharbon: \u00a762 XP \n \u00a77 - \u00a7eLapis: \u00a762 XP \n \u00a77 - \u00a7eNetherrack: \u00a762 XP \n \u00a77 - \u00a7eNether brick: \u00a762 XP \n \u00a77 - \u00a7eOr: \u00a763 XP \n \u00a77 - \u00a7eStone moussue: \u00a763 XP \n \u00a77 - \u00a7ePrismarine: \u00a763 XP\n \u00a77 - \u00a7eRedstone: \u00a764 XP \n \u00a77 - \u00a7eFer: \u00a764 XP \n \u00a77 - \u00a7eDiamant: \u00a764 XP\n \u00a77 - \u00a7eQuartz: \u00a7610 XP \n \u00a77 - \u00a7e\u00c9meraude: \u00a7610 XP\n&8", new HashMap<Enchantment, Integer>(){
            {
                this.put(Enchantment.LURE, 1);
            }
        }), voidAction);
        this.jobsGui.add_element(33, ItemBuilder.ItemCreator(Material.BOOK_AND_QUILL, "\u00a7f\u00a7l\u279c &e&lT\u00e2ches &8- &a&lFermier", "&8\n\u00a77\u27a5 \u00a76Farmer:\n \u00a77 - \u00a7eBl\u00e9: \u00a764 XP \n \u00a77 - \u00a7eCarotte: \u00a764 XP \n \u00a77 - \u00a7ePomme de terre: \u00a764 XP \n \u00a77 - \u00a7eCacao: \u00a764 XP\n \u00a77 - \u00a7eNether Warts: \u00a763 XP\n \u00a77 - \u00a7eCanne \u00e0 sucre: \u00a762 XP\n&8", new HashMap<Enchantment, Integer>(){
            {
                this.put(Enchantment.LURE, 1);
            }
        }), voidAction);
        this.jobsGui.add_element(49, ItemBuilder.ItemCreator(Material.INK_SACK, "\u00a7c\u00a7lRetour", (short)1), new Chat("/menu"));
        this.jobsGui.add_element(22, ItemBuilder.ItemCreator(Material.NETHER_STAR, "\u00a7e\u00a7lJobs Shop"), new Chat("/jobsshop"));
    }

    public static Jobs getInstance() {
        return instance;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "mineur": {
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    commandSender.sendMessage("");
                    commandSender.sendMessage("\u00a77 \u27a5 \u00a76Miner:");
                    commandSender.sendMessage("   \u00a77 - \u00a7eStone: \u00a761 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eCharbon / Lapis / Netherrack / Nether brick: \u00a762 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eOr / Stone moussue / Prismarine: \u00a763 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eRedstone / Fer / Diamant: \u00a764 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eQuartz / \u00c9meraude: \u00a7610 XP");
                    commandSender.sendMessage("");
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    return true;
                }
                case "fermier": {
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    commandSender.sendMessage("");
                    commandSender.sendMessage("\u00a77 \u27a5 \u00a76Farmer:");
                    commandSender.sendMessage("   \u00a77 - \u00a7eBl\u00e9 / Carotte / Pomme de terre / Cacao: \u00a764 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eNether Warts: \u00a763 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eCanne \u00e0 sucre: \u00a762 XP");
                    commandSender.sendMessage("");
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    return true;
                }
                case "chasseur": {
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    commandSender.sendMessage("");
                    commandSender.sendMessage("\u00a77 \u27a5 \u00a76Tuer:");
                    commandSender.sendMessage("   \u00a77 - \u00a7ePoulet / Vache / Cochon / Mouton / Snowman / Poulpe / Lapin: \u00a762 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eSquelette / Araign\u00e9e / Zombie / Blaze / Cave Spider / Champimeuh / PigZombie: \u00a763 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eLoup / Creeper / Enderman / Silverfish / Guardian: \u00a765 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eGhast / Golem: \u00a7610 XP");
                    commandSender.sendMessage("   \u00a77 - \u00a7eJoueur: \u00a76100 XP");
                    commandSender.sendMessage("");
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    return true;
                }
                case "bucheron": {
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    commandSender.sendMessage("");
                    commandSender.sendMessage("\u00a77 \u27a5 \u00a76Casser:");
                    commandSender.sendMessage("   \u00a77 - \u00a7eB\u00fbche: \u00a762 XP");
                    commandSender.sendMessage("");
                    commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
                    return true;
                }
            }
            this.sendHelp(commandSender);
            return true;
        }
        if (args.length == 2) {
            switch (args[0]) {
                case "top": {
                    this.displayTop(commandSender, args[1]);
                    return true;
                }
            }
            this.sendHelp(commandSender);
            return true;
        }
        this.jobsGui.openGUI((Player)commandSender);
        return true;
    }

    public void displayTop(CommandSender commandSender, String jobName) {
        JobType jobType;
        switch (jobName) {
            case "fermier": {
                jobType = JobType.FARMER;
                break;
            }
            case "chasseur": {
                jobType = JobType.HUNTER;
                break;
            }
            case "mineur": {
                jobType = JobType.MINER;
                break;
            }
            case "bucheron": {
                jobType = JobType.LUMBERJACK;
                break;
            }
            default: {
                commandSender.sendMessage("\u00a74\u00bb \u00a7cUsage incorrect: \u00a76/jobs help");
                return;
            }
        }
        commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
        commandSender.sendMessage("\u00a78");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a7eClassement \u00a76" + JobUtils.prettyJobName(jobType));
        commandSender.sendMessage("\u00a78");
        AtomicReference<Integer> current = new AtomicReference<Integer>(1);
        this.plugin.getJobsTop().getJobTopData(jobType).forEach(v -> {
            StringBuilder sb = new StringBuilder();
            sb.append("\u00a76 \u00bb \u00a7e");
            sb.append(current.getAndSet((Integer)current.get() + 1));
            sb.append("# \u00a7c");
            sb.append(v.getPlayerName());
            sb.append(" \u00a77- \u00a7eNiveau \u00a7c");
            sb.append(v.getJobLevel(jobType));
            sb.append(" \u00a78\u00a7o(");
            sb.append(v.getXP(jobType));
            sb.append(" XP)");
            commandSender.sendMessage(sb.toString());
        });
        commandSender.sendMessage("\u00a78");
        commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
    }

    public void sendHelp(CommandSender commandSender) {
        commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a76/jobs\u00a7e: Affiche tes statistiques de jobs");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a76/jobs top <mineur/fermier/chasseur/bucheron>\u00a7e: Affiche les meilleurs joueurs du m\u00e9tier");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a76/jobs mineur\u00a7e: Affiche les informations sur le mineur");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a76/jobs fermier\u00a7e: Affiche les informations sur le fermier");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a76/jobs chasseur\u00a7e: Affiche les informations sur le chasseur");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a76/jobs bucheron\u00a7e: Affiche les informations sur le bucheron");
        commandSender.sendMessage("\u00a77 \u27a5 \u00a76/jobs help\u00a7e: Affiche cette aide");
        commandSender.sendMessage(" \u00a76\u00a7m+           \u00a7e\u00a7m                \u00a77\u00a7m                              \u00a7e\u00a7m              \u00a76\u00a7m           +");
    }
}

