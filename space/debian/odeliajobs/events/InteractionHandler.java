package space.debian.odeliajobs.events;

import com.massivecraft.factions.listeners.FactionsBlockListener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import space.debian.odeliajobs.commands.Jobs;
import space.debian.odeliajobs.objects.types.JobType;
import space.debian.odeliajobs.utils.guis.GUI;
import space.debian.odeliajobs.utils.guis.GUIElement;
import space.debian.odeliajobs.utils.storage.Config;
import space.debian.odeliajobs.utils.storage.UserData;

public class InteractionHandler
implements Listener {
    @EventHandler(priority=EventPriority.MONITOR)
    public void onEntityKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }
        Config.entityDeathEvent.forEach((job, rewards) -> rewards.forEach((entity, reward) -> {
            if (entity.equals((Object)event.getEntity().getType())) {
                reward.rewardPlayer((JobType)((Object)job), UserData.getProfileMap().get(event.getEntity().getKiller().getName()));
            }
        }));
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (event.isCancelled() || event.getPlayer() == null) {
            return;
        }
        if (event.getPlayer().getItemInHand().getEnchantments().containsKey((Object)Enchantment.SILK_TOUCH)) {
            return;
        }
        if (!FactionsBlockListener.playerCanBuildDestroyBlock((Player)event.getPlayer(), (Location)event.getBlock().getLocation(), (String)"destroy", (boolean)true)) {
            return;
        }
        Config.blockBreakEvent.forEach((job, rewards) -> rewards.forEach((entity, reward) -> {
            if (entity.equals((Object)event.getBlock().getType())) {
                reward.rewardPlayer((JobType)((Object)job), UserData.getProfileMap().get(event.getPlayer().getName()));
            }
        }));
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onFarmBlockBreakEvent(BlockBreakEvent event) {
        if (event.isCancelled() || event.getPlayer() == null) {
            return;
        }
        if (event.getBlock().getType().equals((Object)Material.COCOA) && event.getBlock().getData() < 7 || !event.getBlock().getType().equals((Object)Material.COCOA) && !event.getBlock().getType().equals((Object)Material.NETHER_WARTS) && event.getBlock().getData() < 6 || event.getBlock().getType().equals((Object)Material.NETHER_WARTS) && event.getBlock().getData() < 3) {
            return;
        }
        if (!FactionsBlockListener.playerCanBuildDestroyBlock((Player)event.getPlayer(), (Location)event.getBlock().getLocation(), (String)"destroy", (boolean)true)) {
            return;
        }
        Config.farmBlockBreakEvent.forEach((job, rewards) -> rewards.forEach((entity, reward) -> {
            if (entity.equals((Object)event.getBlock().getType())) {
                reward.rewardPlayer((JobType)((Object)job), UserData.getProfileMap().get(event.getPlayer().getName()));
            }
        }));
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onFurnaceExtractEvent(FurnaceExtractEvent event) {
        if (event.getPlayer() == null) {
            return;
        }
        Config.furnaceExtractEvent.forEach((job, rewards) -> rewards.forEach((entity, reward) -> {
            if (entity.equals((Object)event.getItemType())) {
                for (int i = 0; i < event.getItemAmount(); ++i) {
                    reward.rewardPlayer((JobType)((Object)job), UserData.getProfileMap().get(event.getPlayer().getName()));
                }
            }
        }));
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled() || event.getPlayer() == null) {
            return;
        }
        Config.blockPlaceEvent.forEach((job, rewards) -> rewards.forEach((entity, reward) -> {
            if (entity.equals((Object)event.getBlock().getType())) {
                reward.rewardPlayer((JobType)((Object)job), UserData.getProfileMap().get(event.getPlayer().getName()));
            }
        }));
    }

    @EventHandler
    public void MenuGUIInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getClickedInventory().getType() == InventoryType.PLAYER) {
            return;
        }
        Player p = (Player)e.getWhoClicked();
        String invName = e.getInventory().getName();
        int clicked_slot = e.getSlot();
        if (invName.equals("\u00a76\u00a7lMENU \u00a78- \u00a7eJobs")) {
            e.setCancelled(true);
            GUI currentGUI = Jobs.getInstance().getJobsGui();
            if (!currentGUI.elements.containsKey(clicked_slot)) {
                return;
            }
            GUIElement currentElem = currentGUI.elements.get(clicked_slot);
            currentElem.getAction().handle(p);
        }
    }
}

