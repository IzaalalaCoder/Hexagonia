package hex.model.game;

import hex.model.board.cell.Cell;
import hex.model.game.player.AbstractPlayer;

public class Action {

    // ATTRIBUTES

    private final Cell c;
    private final AbstractPlayer player;

    // CONSTRUCTORS

    public Action(Cell c, AbstractPlayer p) {
        this.c = c;
        this.player = p;
    }

    // REQUESTS

    public Cell getCell() {
        return c;
    }

    public AbstractPlayer getPlayer() {
        return player;
    }
}