package hex.model.game.theoric.algorithm.alphabeta;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.LabelPlayer;
import hex.model.game.theoric.structure.node.Status;

public class AlphaBeta extends AbstractTheory {

    // CONSTRUCTOR

    public AlphaBeta(Game game, Level level) {
        super(game, level);
        this.analyze(tree.getRoot());
    }

    // UTILS

    private void analyze(Node root) {
        this.alphaBeta(root, -700.0, +700.0);
        this.chooseBoard(root);
    }

    private Double alphaBeta(Node root, Double alpha, Double beta) {
        if (root.getStatus() != Status.LEAF) {
            int k = 1;
            if (root.getLabelPlayer() == LabelPlayer.MAX) {
                while (alpha < beta && k <= root.getSuccessor().size()) {
                    alpha = Math.max(alpha, alphaBeta(root.getSuccessor().get(k - 1), alpha, beta));
                    k++;
                }
                root.setHeuristicValue(alpha);
                return alpha;
            } else {
                while (alpha < beta && k <= root.getSuccessor().size()) {
                    beta = Math.min(beta, alphaBeta(root.getSuccessor().get(k - 1), alpha, beta));
                    k++;
                }
                root.setHeuristicValue(beta);
                return beta;
            }
        }
        return root.getHeuristicValue();
    }
}