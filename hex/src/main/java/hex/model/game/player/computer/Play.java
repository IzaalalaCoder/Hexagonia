package hex.model.game.player.computer;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Action;
import hex.model.game.theoric.algorithm.RandomAction;

public class Play {

    // ATTRIBUTES

    Level level;
    RandomAction randomize;
    Board board;
    Action action;

    // CONSTRUCTOR

    public Play(Level level, Board b, Computer computer) {
        this.board = b;
        this.level = level;
        this.action = null;
        this.randomize = new RandomAction(computer);
    }

    // COMMANDS

    public Action getAction() {
        Action chooseAction;
        if (this.action != null) {
            chooseAction = this.action;
        } else {
            return null;
        }
        this.action = null;
        return chooseAction;
    }

    public void play() {
        int percent = this.getPercentEmptyCase();
        if (percent > this.level.getPercent()) {
            this.randomize.chooseCell(this.board);
            this.action = this.randomize.getChooseCell();
        } else {
        }

    }

    // UTILS

    private int getPercentEmptyCase() {
        int count = 0;

        for (Cell[] cells : this.board.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    count++;
                }
            }
        }


        int size = this.board.getGrid().length
            * this.board.getGrid().length;

        return ((count * 100) / size);
    }
}
