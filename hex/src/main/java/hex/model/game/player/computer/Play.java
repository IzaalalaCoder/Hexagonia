package hex.model.game.player.computer;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Action;
import hex.model.game.theoric.RandomAction;

public class Play {

    Level level;
    RandomAction randomize;
    Board board;
    Action action;

    // CONSTRUCTOR

    public Play(Level level, Board b, Computer computer) {
        if (b == null) System.out.println("jidsdf");
        this.board = b;
        this.level = level;
        this.action = null;
        this.randomize = new RandomAction(computer);
    }

    // COMMANDS

    public Action getAction() {
        return this.action;
    }

    public void play() {
        if (this.getPercentFullCase() < this.level.getPercent()) {
            this.randomize.chooseCell(this.board);
            this.action = this.randomize.getChooseCell();
        }

    }

    // UTILS

    private int getPercentFullCase() {
        int count = 0;

        for (Cell[] cells : this.board.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.PLAYER) {
                    count++;
                }
            }
        }

        int size = this.board.getGrid().length
            * this.board.getGrid().length;

        return (count / size) * 100;
    }
}
