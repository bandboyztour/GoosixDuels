package net.goosix;

import net.goosix.game.Game;
import net.goosix.game.StickGameImpl;

public enum PlayerState {
    IN_LOBBY(null),
    STICK(new StickGameImpl());

    private Game game;

    PlayerState(Game game) {
        this.game = game;
    }

    public Game getGameImpl(){
        return game;
    }

}
