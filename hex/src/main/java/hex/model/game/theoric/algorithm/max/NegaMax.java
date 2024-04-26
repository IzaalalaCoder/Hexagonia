package hex.model.game.theoric.algorithm.max;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.Status;
import hex.model.game.theoric.structure.tree.Arborescence;

public class NegaMax extends AbstractTheory {

    // CONSTRUCTOR

    public NegaMax(Game game, Level level) {
        super(game, level);
        Arborescence a = new Arborescence(game);
        this.analyze(a.createArborescence(level));
    }   

    // UTILS
    
    private void analyze(Node root) {
        this.negamax(root);
        this.chooseBoard(root);
    }

    private void chooseBoard(Node root) {
        Node nodeWithMaxHeuristicValue = root.getSuccessor().get(0);

        for (Node node : root.getSuccessor()) {
            if (node.getHeuristicValue() > nodeWithMaxHeuristicValue.getHeuristicValue()) {
                nodeWithMaxHeuristicValue = node;
            }
        }

        chooseBoard = nodeWithMaxHeuristicValue.getActualBoard();
    }
    
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

        //chooseBoard = root.getActualBoard();
        return val;
    }
}
