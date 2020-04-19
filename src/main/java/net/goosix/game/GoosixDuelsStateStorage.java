package net.goosix.game;

import com.avaje.ebeaninternal.server.lib.util.NotFoundException;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.NonNull;
import net.goosix.GoosixDuelsPlugin;
import net.goosix.PlayerState;
import net.goosix.arena.GameArena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoosixDuelsStateStorage {

    private Map<Player, PlayerState> gamePlayerMap;
    private Map<PlayerState, List<GameArena>> freeArenaStateMap;
    private GoosixDuelsPlugin main;
    private BiMap<Player, Player> duelsPlayers;

    public GoosixDuelsStateStorage(@NonNull GoosixDuelsPlugin plugin) {
        this.main = plugin;
        this.gamePlayerMap = new HashMap<>();
        this.freeArenaStateMap = new HashMap<>();
        this.duelsPlayers = HashBiMap.create();
    }

    public void addDuel(Player player1, Player player2) {
        duelsPlayers.put(player1, player2);
    }

    public void removeDuel(Player player) {
        duelsPlayers.remove(duelsPlayers.inverse().get(player));
        duelsPlayers.remove(player);
    }

    public Player findOpponent(Player player) {
        Player opponent = duelsPlayers.get(player);
        if (opponent == null){
            Player opp = duelsPlayers.inverse().get(player);
            if (opp != null){
                return opp;
            } else {
                return null;
            }
        } else {
            return opponent;
        }
    }

    public void setState(@NonNull PlayerState state, @NonNull Player... players) {
        for (Player p : players) {
            gamePlayerMap.put(p, state);
        }
    }

    public void setLobbyState(@NonNull Player... players) {
        for (Player p : players) {
            gamePlayerMap.remove(p);
            gamePlayerMap.put(p, PlayerState.IN_LOBBY);
        }
    }

    public void initFillingArenaStorage() {
        for (String s : main.getConfig().getStringList("arenas")) {
            initArena(s);
        }
    }

    public void clearPlayerData(@NonNull Player player) {
        gamePlayerMap.remove(player);
    }

    public GameArena findFreeArena(@NonNull PlayerState arenaType) throws Exception {
        if (!freeArenaStateMap.containsKey(arenaType)) {
            throw new NotFoundException("Arena type not found"); // не уверен что это вообще вероятно
        }
        if (freeArenaStateMap.get(arenaType).isEmpty()) {
            return null;
        } else {
            for (GameArena arena : freeArenaStateMap.get(arenaType)) {
                return arena; // как взять иначе первую из листа
            }
        }
        throw new Exception("Unexpected shit #1");
    }

    public PlayerState findPlayerState(@NonNull Player player) {
        return gamePlayerMap.get(player);
    }


    public void initArena(@NonNull String arenaString) {
        String[] splittedArenaString = arenaString.replace(" ", "").split(";");
        String[] moreSplitted = splittedArenaString[2].split("|");
        PlayerState arenaType = PlayerState.valueOf(splittedArenaString[0]);
        World world = Bukkit.getWorld(splittedArenaString[1]);
        Location playerSpawnLoc1;
        {
            String[] moooreSplitted = moreSplitted[0].split(":");
            double x = Double.parseDouble(moooreSplitted[0]);
            double y = Double.parseDouble(moooreSplitted[1]);
            double z = Double.parseDouble(moooreSplitted[2]);
            playerSpawnLoc1 = new Location(world, x, y, z);
        }
        Location playerSpawnLoc2;
        {
            String[] moooreSplitted = moreSplitted[1].split(":");
            double x = Double.parseDouble(moooreSplitted[0]);
            double y = Double.parseDouble(moooreSplitted[1]);
            double z = Double.parseDouble(moooreSplitted[2]);
            playerSpawnLoc2 = new Location(world, x, y, z);
        }
        if (!freeArenaStateMap.containsKey(arenaType)) {
            freeArenaStateMap.put(arenaType, new ArrayList<>());
        }
        freeArenaStateMap.get(arenaType).add(new GameArena(PlayerState.valueOf(splittedArenaString[0]),
                playerSpawnLoc1, playerSpawnLoc2));
    }
}
