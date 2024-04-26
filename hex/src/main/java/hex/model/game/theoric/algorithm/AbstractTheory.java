package hex.model.game.theoric.algorithm;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Game;
import hex.model.game.player.computer.Level;

public abstract class AbstractTheory implements Theory {

    // ATTRIBUTES

    protected Board startBoard;
    protected Board chooseBoard;

    // CONSTRUCTORS 

    public AbstractTheory(Game game, Level level) {
        chooseBoard = null;
        startBoard = game.getBoard();
    }
    
    // REQUESTS

    @Override
    public Cell getCell() {
        return this.getChooseCell();
    }

    // UTILS

    private Cell getChooseCell() {
        Cell choose = null;

        for (Cell[] cells : this.chooseBoard.getGrid()) {
            for (Cell c : cells) {
                int i = c.getAbscissa();
                int j = c.getOrdinate();
                if (this.startBoard.getGrid()[i][j].getState() == State.EMPTY 
                    && c.getState() == State.PLAYER) {
                        choose = c;
                        break;
                }
            }
            if (choose != null) {
                break;
            }
        }

        if (choose == null) {
            System.out.println("START");
            System.out.println(startBoard.toString());


            System.out.println("END");
            System.out.println(chooseBoard.toString());
        }

        return choose;
    }
}
