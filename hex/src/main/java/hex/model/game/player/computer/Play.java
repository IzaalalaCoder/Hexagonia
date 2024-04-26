package hex.model.game.player.computer;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Action;
import hex.model.game.Game;
import hex.model.game.theoric.algorithm.RandomAction;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.algorithm.max.Minimax;
import hex.model.game.theoric.algorithm.sss.ThreeSStar;

public class Play {

    // ATTRIBUTES

    Level level;
    RandomAction randomize;
    Game game;
    Action action;

    // CONSTRUCTOR

    public Play(Level level, Game game, Computer computer) {
        this.game = game;
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
            this.randomize.chooseCell(this.game.getBoard());
            this.action = this.randomize.getChooseCell();
        } else {
            
            Theory theoric = new ThreeSStar(game, level);
            this.action  = new Action(theoric.getCell(), game.getComputer());
        }

    }

    // UTILS

    private int getPercentEmptyCase() {
        int count = 0;

        for (Cell[] cells : this.game.getBoard().getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    count++;
                }
            }
        }


        int size = this.game.getBoard().getGrid().length
            * this.game.getBoard().getGrid().length;

        return ((count * 100) / size);
    }
}
