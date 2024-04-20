package hex.model.game.theoric.algorithm.max;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.game.theoric.algorithm.Theory;

public class Minimax implements Theory {

    // ATTRIBUTES

    @SuppressWarnings("unused")
    private final Board board;
    //private Node root;

    // CONSTRUCTORS

    public Minimax(Board board) {
        this.board = board;
    }

    // REQUESTS

    @Override
    public Cell getChooseCell() {
        return null;
    }

    // COMMANDS

    @Override
    public void readBoardGame(Board game) {
        
    }

    @Override
    public void analyze() {
        
    }
    
}
