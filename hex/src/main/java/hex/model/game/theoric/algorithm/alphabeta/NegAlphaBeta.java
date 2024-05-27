package hex.model.game.theoric.algorithm.alphabeta;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.Status;

public class NegAlphaBeta extends AbstractTheory {

    // CONSTRUCTOR

    public NegAlphaBeta(Game game, Level level) {
        super(game, level);
        this.analyze(tree.getRoot());
    }

    // UTILS

    private void analyze(Node root) {
        this.negAlphaBeta(root, MIN_HEURISTIC_VALUE, MAX_HEURISTIC_VALUE);
        this.chooseBoard(root);
    }

    private Double negAlphaBeta(Node root, Double alpha, Double beta) {
        if (root.getStatus() != Status.LEAF) {
            int k = 1;
            double val = -700.0;
            while (alpha < beta && k <= root.getSuccessor().size()) {
                val = Math.max(val, -negAlphaBeta(root.getSuccessor().get(k - 1), -beta, -alpha));
                alpha = Math.max(alpha, val);
                k++;
            }
            root.setHeuristicValue(val);
            return val;
        }
        return root.getHeuristicValue();
    }
}