package hex.model.game.player.computer;

import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Action;
import hex.model.game.Game;
import hex.model.game.theoric.algorithm.RandomAction;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.algorithm.sss.ThreeSStar;

public class Play {

    // ATTRIBUTES

    private final Level level;
    private final RandomAction randomize;
    private final Game game;

    // CONSTRUCTOR

    public Play(Level level, Game game, Computer computer) {
        this.game = game;
        this.level = level;
        this.randomize = new RandomAction(computer);
    }

    // REQUESTS

    public Action play() {
        if (shouldUseAdvancedAlgorithm()) {
            Theory theory = new ThreeSStar(game, level);
            Cell chosenCell = theory.getCell();
            return new Action(chosenCell, game.getComputer());
        } else {
            return chooseRandomAction();
        }
    }

    // UTILS

    private boolean shouldUseAdvancedAlgorithm() {
        int percentEmptyCells = getPercentEmptyCase();
        return percentEmptyCells <= 95;
    }

    private Action chooseRandomAction() {
        randomize.chooseCell(game.getBoard());
        return randomize.getChooseCell();
    }

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