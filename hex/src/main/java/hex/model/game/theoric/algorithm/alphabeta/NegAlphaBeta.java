package hex.model.game.theoric.algorithm.alphabeta;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.Status;
import hex.model.game.theoric.structure.tree.Arborescence;

public class NegAlphaBeta extends AbstractTheory {

    // CONSTRUCTOR

    public NegAlphaBeta(Game game, Level level) {
        super(game, level);
        Arborescence a = new Arborescence(game);
        this.analyze(a.createArborescence(level));
    }

    // UTILS

    private void analyze(Node root) {
        this.negAlphaBeta(root, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    private Double negAlphaBeta(Node root, Double alpha, Double beta) {
        if (root.getStatus() != Status.LEAF) {
            int k = 1;
            Double val = Double.MIN_VALUE;
            while (alpha < beta && k <= root.getSuccessor().size()) {
                val = Math.max(val, -negAlphaBeta(root.getSuccessor().get(k - 1), -beta, -alpha));
                alpha = Math.max(alpha, val);
                k++;
            }
            return val;
        }

        chooseBoard = root.getActualBoard();
        return root.getHeuristicValue();
    }
}
