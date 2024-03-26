package hex.model.game;

import hex.model.game.player.AbstractPlayer;

public class Action {

    // ATTRIBUTES

    private final int abscissa;
    private final int ordinate;

    private final AbstractPlayer player;

    // CONSTRUCTORS

    public Action(int x, int y, AbstractPlayer p) {
        this.abscissa = x;
        this.ordinate = y;
        this.player = p;
    }

    // REQUESTS

    public int getAbscissa() {
        return abscissa;
    }

    public int getOrdinate() {
        return ordinate;
    }

    public AbstractPlayer getPlayer() {
        return player;
    }
}
