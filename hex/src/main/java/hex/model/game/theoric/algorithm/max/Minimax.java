package hex.model.game.theoric.algorithm.max;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.algorithm.tree.Arborescence;
import hex.model.game.theoric.structure.Node;


public class Minimax implements Theory {

    // ATTRIBUTES

    private final Node root; 

    // CONSTRUCTOR

    public Minimax(Board board, Level level) {
        Arborescence a = new Arborescence(board);
        root = a.createArborescence(level);
    }

    // REQUESTS

    @Override
    public Cell getChooseCell() {
        return null;
        
    }

    // COMMANDS

    @Override
    public void analyze() {
    }
    
}
