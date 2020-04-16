package net.goosix.listener;

import net.goosix.GoosixDuelsPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    private GoosixDuelsPlugin main;

    public MainListener(GoosixDuelsPlugin goosixDuelsPlugin) {
        this.main = goosixDuelsPlugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().isOp()){
            return; // хочу если игрок оп то то что дальше будет отменять тупо не дошло, не отменит ивент же, надеюсь
        }
        // если не в стик режиме отмена
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        //отмена если не на стик дуели, добавление в спец лист
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //отправка игрока на корды лобби ВСЕГДА, выдача стандарт итемов которые нельзя перетаскивать

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // если игрок в игре = победа второго игрока
    }


    @EventHandler
    public void onEntityDamaged(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {

        }
    }

    @EventHandler
    public void onEntityByEntityDamaged(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        //победа оппонента
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }
}

