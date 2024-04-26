package hex.model.game.theoric.algorithm.max;

import hex.model.board.cell.Cell;
import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.Status;
import hex.model.game.theoric.structure.tree.Arborescence;

public class NegaMax implements Theory {

    // ATTRIBUTES

    private Cell chooseCell;

    // CONSTRUCTOR

    public NegaMax(Game game, Level level) {
        Arborescence a = new Arborescence(game);
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
