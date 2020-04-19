package net.goosix.listener;

import net.goosix.CoordsUtil;
import net.goosix.GoosixDuelsPlugin;
import net.goosix.PlayerState;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    private GoosixDuelsPlugin main;
    private Location lobbyLocation;

    public MainListener(GoosixDuelsPlugin goosixDuelsPlugin) {
        this.main = goosixDuelsPlugin;
        this.lobbyLocation = CoordsUtil.toLocationFromString(main.getConfig().getString("lobbyCoords"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().isOp()){
            return;
        }
        PlayerState playerState = main.getStateStorage().findPlayerState(player);
        if (playerState == PlayerState.STICK){
            playerState.getGameImpl().playerBlockBreak(player, event.getBlock());
            return;
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()){
            return;
        }
        PlayerState playerState = main.getStateStorage().findPlayerState(player);
        if (playerState == PlayerState.STICK){
            playerState.getGameImpl().playerBlockPlace(player, event.getBlock());
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //отправка игрока на корды лобби ВСЕГДА, выдача стандарт итемов которые нельзя перетаскивать
        Player player = event.getPlayer();
        player.teleport(lobbyLocation);
        main.getStateStorage().setLobbyState(player);

      // дать стандарт вещи которые
        
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        // если игрок в игре = победа второго игрока
        // передача логике того режима где он был
        main.getStateStorage().clearPlayerData(player);
        // todo не забыть очистить с листов, мап, очередей
    }


    @EventHandler
    public void onEntityDamaged(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (main.getStateStorage().findPlayerState(player) == PlayerState.IN_LOBBY){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent event){ // нужн будет в гуишках
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryDrag(InventoryDragEvent event){ // нужн будет в гуишках
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerState playerState = main.getStateStorage().findPlayerState(player);
        if (playerState == PlayerState.IN_LOBBY){
            player.teleport(lobbyLocation);
        }
        playerState.getGameImpl().playerDeath(player);
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

    //todo дохрена ивентов которые нужно отменить....
}

