package hex.game;

import hex.game.player.PlayerName;

public class Action {

    // ATTRIBUTS

    private final int abscissa;
    private final int ordinate;

    private final PlayerName player;

    // CONSTRUCTEUR

    public Action(int x, int y, PlayerName p) {
        this.abscissa = x;
        this.ordinate = y;
        this.player = p;
    }

    // REQUETES

    public int getAbscissa() {
        return abscissa;
    }

    public int getOrdinate() {
        return ordinate;
    }

    public PlayerName getPlayer() {
        return player;
    }
}
