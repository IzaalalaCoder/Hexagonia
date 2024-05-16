package hex.model.game.theoric.algorithm.max;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.Status;

public class NegaMax extends AbstractTheory {

    // CONSTRUCTOR

    public NegaMax(Game game, Level level) {
        super(game, level);
        this.analyze(tree.getRoot());
    }   

    // UTILS
    
    private void analyze(Node root) {
        this.negamax(root);
        this.chooseBoard(root);
    }
    
    private Double negamax(Node root) {
        Double val;
        if (root.getStatus() == Status.LEAF) {
            val = root.getHeuristicValue();
        } else {
            val = -700.0;
            for (Node n : root.getSuccessor()) {
                val = Math.max(val, -1.0 * this.negamax(n));
            }
        }
        root.setHeuristicValue(val);
        return val;
    }
}