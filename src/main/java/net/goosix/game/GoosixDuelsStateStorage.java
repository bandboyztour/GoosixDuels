package net.goosix.game;

import net.goosix.GoosixDuelsPlugin;
import net.goosix.PlayerState;
import net.goosix.arena.GameArena;
import net.goosix.arena.GaneArenaState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GoosixDuelsStateStorage {

    private Map<Player, PlayerState> gamePlayerMap;
    private Map<GameArena, GaneArenaState> arenaStateMap;
    private GoosixDuelsPlugin main;

    public GoosixDuelsStateStorage(GoosixDuelsPlugin plugin) {
        this.gamePlayerMap = new HashMap<>();
        this.arenaStateMap = new HashMap<>();
        this.main = plugin;
    }

    public void setState(PlayerState state, Player... players) {
        for (Player p : players) {
            gamePlayerMap.put(p, state);
        }
    }

    public void setLobbyState(Player... players) {
        for (Player p : players) {
            gamePlayerMap.remove(p);
            gamePlayerMap.put(p, PlayerState.IN_LOBBY);
        }
    }

    public void initFillingArenaStorage(){
        for (String s : main.getConfig().getStringList("arenas")){
            initArena(s);
        }
    }

    public void initArena(String arenaString) {
        String[] splittedArenaString = arenaString.split(";");
        String[] moreSplitted = splittedArenaString[2].split("|");
        Location playerSpawnLoc1;
        {
            World world = Bukkit.getWorld(splittedArenaString[1]);
            String[] moooreSplitted = moreSplitted[0].split(":");
            double x = Double.parseDouble(moooreSplitted[0]);
            double y = Double.parseDouble(moooreSplitted[1]);
            double z = Double.parseDouble(moooreSplitted[2]);
            playerSpawnLoc1 = new Location(world, x, y, z);
        }
        Location playerSpawnLoc2;
        {
            World world = Bukkit.getWorld(splittedArenaString[1]);
            String[] moooreSplitted = moreSplitted[1].split(":");
            double x = Double.parseDouble(moooreSplitted[0]);
            double y = Double.parseDouble(moooreSplitted[1]);
            double z = Double.parseDouble(moooreSplitted[2]);
            playerSpawnLoc2 = new Location(world, x, y, z);
        }
        this.arenaStateMap.put(new GameArena(PlayerState.valueOf(splittedArenaString[0]),
                playerSpawnLoc1, playerSpawnLoc2), GaneArenaState.FREE);
    }
}
