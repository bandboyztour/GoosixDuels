package net.goosix;

import org.bukkit.entity.Player;

public class PlayerUtils {

    public static void sendMessageToPlayers(String msg, Player... players){
        for(Player player : players){
            player.sendMessage(msg);
        }
    }

}
