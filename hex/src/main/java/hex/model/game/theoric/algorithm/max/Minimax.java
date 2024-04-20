package hex.model.game.theoric.algorithm.max;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.structure.Node;

@SuppressWarnings("unused")
public class Minimax implements Theory {

    // ATTRIBUTES

    
    private final Board board;
    private Node root;

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
    public void readBoardGame() {
        for (Cell[] cells : this.board.getGrid()) {
            for (Cell c : cells) {

            }
        }
    }

    @Override
    public void analyze() {
        
    }
    
}
