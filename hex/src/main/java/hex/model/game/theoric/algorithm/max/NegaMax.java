package hex.model.game.theoric.algorithm.max;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.algorithm.tree.Arborescence;
import hex.model.game.theoric.structure.Node;
import hex.model.game.theoric.structure.util.LabelPlayer;
import hex.model.game.theoric.structure.util.Status;

public class Negamax implements Theory {

    // ATTRIBUTES

    private Cell chooseCell;

    // CONSTRUCTOR

    public Negamax(Board board, Level level) {
        Arborescence a = new Arborescence(board);
        this.analyze(a.createArborescence(level));
        chooseCell = null;
    }   

    // REQUESTS

    @Override
    public Cell getChooseCell() {
        return chooseCell;
        
    }

    // COMMANDS

    @Override
    public void analyze(Node root) {
        this.negamax(root);
    }

    // UTILS
    
    private Double negamax(Node root) {
        Double val;
        if (root.getStatus() == Status.LEAF) {
            // calculate heuristic value
            val = root.getHeuristicValue();
        } else {
            val = Double.MIN_VALUE;
            for (Node n : root.getSuccessor()) {
                val = Math.max(val, -this.negamax(n));
            }
        }
        return val;
    }
}
