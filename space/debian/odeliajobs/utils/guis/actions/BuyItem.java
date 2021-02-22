package space.debian.odeliajobs.utils.guis.actions;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import space.debian.odeliajobs.Main;
import space.debian.odeliajobs.utils.guis.Action;
import space.debian.odeliajobs.utils.guis.ClickAction;

public class BuyItem
implements Action,
ClickAction {
    private final String permissionNeeded;
    private final ItemStack itemStack;
    private final Integer farmPrice;
    private final Integer premiumPrice;

    public BuyItem(String permissionNeeded, ItemStack itemStack, Integer farmPrice, Integer premiumPrice) {
        this.permissionNeeded = permissionNeeded;
        this.itemStack = itemStack;
        this.farmPrice = farmPrice;
        this.premiumPrice = premiumPrice;
    }

    @Override
    public void handle(Player player) {
    }

    @Override
    public void handle(Player player, ClickType clickType) {
        if (clickType != ClickType.LEFT && clickType != ClickType.RIGHT) {
            return;
        }
        if (!player.hasPermission(this.permissionNeeded)) {
            player.sendMessage("\u00a74\u00bb \u00a7cVous n'avez pas la permission d'acheter ceci !");
            return;
        }
        RegisteredServiceProvider economyProvider = ((Main)Main.getPlugin(Main.class)).getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider == null) {
            player.sendMessage("\u00a74\u00bb \u00a7cUne erreur est survenue. Merci de signaler celle-ci \u00e0 l'administration.");
            Main.getInstance().getLogger().severe("Vault is not available. Please reload to unload the GUIs module.");
            return;
        }
        Economy economy = (Economy)economyProvider.getProvider();
        if (clickType == ClickType.LEFT && economy.getBalance((OfflinePlayer)player) < (double)this.farmPrice.intValue()) {
            player.sendMessage("\u00a74\u00bb \u00a7cVous n'avez pas assez d'argent pour acheter ceci !");
            return;
        }
        if (clickType == ClickType.RIGHT && Main.getInstance().getPlayerPoints().getAPI().look(player.getName()) < this.premiumPrice) {
            player.sendMessage("\u00a74\u00bb \u00a7cVous n'avez pas assez de pi\u00e8ces d'or pour acheter ceci !");
            return;
        }
        ItemStack stack = this.itemStack.clone();
        if (player.getInventory().addItem(new ItemStack[]{stack}).size() != 0) {
            player.getWorld().dropItem(player.getLocation(), stack);
        }
        if (clickType == ClickType.LEFT) {
            economy.withdrawPlayer((OfflinePlayer)player, (double)this.farmPrice.intValue());
        } else {
            Main.getInstance().getPlayerPoints().getAPI().take(player.getName(), this.premiumPrice.intValue());
        }
        player.sendMessage("\u00a72\u00bb \u00a7aVous avez achet\u00e9 " + this.itemStack.getAmount() + "x + " + this.itemStack.getType().toString() + " !");
    }
}

