package hex.model.game.theoric.algorithm;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Action;
import hex.model.game.player.computer.Computer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAction {

    // ATTRIBUTES

    private final List<Cell> emptyCells;
    private final Computer player;
    private Cell chooseCell;

    // CONSTRUCTORS

    public RandomAction(Computer player) {
        this.emptyCells = new ArrayList<>();
        this.chooseCell = null;
        this.player = player;
    }

    // REQUESTS

    public Action getChooseCell() {
        return new Action(chooseCell, player);
    }

    // COMMANDS

    public void chooseCell(Board b) {
        this.chooseCell = this.choose(b);
    }

    // UTILS

    private void readBoard(Board b) {
        for (Cell[] cells : b.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    this.emptyCells.add(c);
                }
            }
        }
    }

    private Cell choose(Board b) {
        this.readBoard(b);
        Random r = new Random();

        int index = r.nextInt(this.emptyCells.size());
        Cell action = this.emptyCells.get(index);
        this.emptyCells.clear();
        return action;
    }
}