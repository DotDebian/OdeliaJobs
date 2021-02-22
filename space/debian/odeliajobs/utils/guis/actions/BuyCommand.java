package space.debian.odeliajobs.utils.guis.actions;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.plugin.RegisteredServiceProvider;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.utils.guis.Action;
import space.debian.odeliajobs.utils.guis.ClickAction;

public class BuyCommand
implements Action,
ClickAction {
    private String permissionNeeded;
    private String negativePermission;
    private String commands;
    private Integer farmPrice;
    private Integer premiumPrice;

    public BuyCommand(String permissionNeeded, String negativePermission, String commands, Integer farmPrice, Integer premiumPrice) {
        this.permissionNeeded = permissionNeeded;
        this.negativePermission = negativePermission;
        this.commands = commands;
        this.farmPrice = farmPrice;
        this.premiumPrice = premiumPrice;
    }

    @Override
    public void handle(Player player) {
    }

    @Override
    public void handle(Player player, ClickType clickType) {
        if (clickType != ClickType.LEFT && clickType != ClickType.RIGHT || this.negativePermission != null && player.hasPermission(this.negativePermission)) {
            return;
        }
        if (!this.permissionNeeded.equalsIgnoreCase("null") && !player.hasPermission(this.permissionNeeded)) {
            player.sendMessage("\u00a74\u00bb \u00a7cVous n'avez pas la permission d'acheter ceci !");
            return;
        }
        RegisteredServiceProvider economyProvider = Main.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider == null) {
            player.sendMessage("\u00a74\u00bb \u00a7cUne erreur est survenue. Merci de signaler celle-ci \u00e0 l'administration.");
            Main.getInstance().getLogger().severe("Vault is not available. Please reload to unload the GUIs module.");
            return;
        }
        Economy economy = (Economy)economyProvider.getProvider();
        if (clickType == ClickType.LEFT && this.farmPrice == -1) {
            return;
        }
        if (clickType == ClickType.RIGHT && this.premiumPrice == -1) {
            return;
        }
        if (clickType == ClickType.LEFT && economy.getBalance((OfflinePlayer)player) < (double)this.farmPrice.intValue()) {
            player.sendMessage("\u00a74\u00bb \u00a7cVous n'avez pas assez d'argent pour acheter ceci !");
            return;
        }
        if (clickType == ClickType.RIGHT && Main.getInstance().getPlayerPoints().getAPI().look(player.getName()) < this.premiumPrice) {
            player.sendMessage("\u00a74\u00bb \u00a7cVous n'avez pas assez de pi\u00e8ces d'or pour acheter ceci !");
            return;
        }
        for (String command : this.commands.split("&&")) {
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("%player_name%", player.getName()));
        }
        if (clickType == ClickType.LEFT) {
            economy.withdrawPlayer((OfflinePlayer)player, (double)this.farmPrice.intValue());
        } else {
            Main.getInstance().getPlayerPoints().getAPI().take(player.getName(), this.premiumPrice.intValue());
        }
    }
}

