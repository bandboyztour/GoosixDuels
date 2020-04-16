package net.goosix.arena;

import net.goosix.PlayerState;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameArena {

    private final PlayerState arenaType;
    private Location playerSpawnLocation1;
    private Location playerSpawnLocation2;


    public GameArena(PlayerState playerState, Location playerSpawnLocation1, Location playerSpawnLocation2){
        this.arenaType = playerState;
        this.playerSpawnLocation1 = playerSpawnLocation1;
        this.playerSpawnLocation2 = playerSpawnLocation2;
    }

    public void spawn(Player player1, Player player2){
        player1.teleport(playerSpawnLocation1);
        player2.teleport(playerSpawnLocation2);
    }

}
