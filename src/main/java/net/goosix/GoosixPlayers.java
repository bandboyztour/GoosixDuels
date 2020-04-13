package net.goosix;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GoosixPlayers {

    private Map<Player, PlayerState> duelsPlayers;

    public GoosixPlayers(){
        duelsPlayers = new HashMap<>();
    }

}
