package hex.model.game.theoric.algorithm.alphabeta;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.LabelPlayer;
import hex.model.game.theoric.structure.node.util.Status;
import hex.model.game.theoric.structure.tree.Arborescence;

public class AlphaBeta extends AbstractTheory {

    // CONSTRUCTOR

    public AlphaBeta(Game game, Level level) {
        super(game, level);
        Arborescence a = new Arborescence(game);
        this.analyze(a.createArborescence(level));
    }

    // UTILS

    private void analyze(Node root) {
        this.alphaBeta(root, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    private Double alphaBeta(Node root, Double alpha, Double beta) {
        if (root.getStatus() != Status.LEAF) {
            if (root.getLabelPlayer() == LabelPlayer.MAX) {
                int k = 1;
                while (alpha < beta && k <= root.getSuccessor().size()) {
                    alpha = Math.max(alpha, alphaBeta(root.getSuccessor().get(k - 1), alpha, beta));
                    k++;
                }
                return alpha;
            } else {
                int k = 1;
                while (alpha < beta && k <= root.getSuccessor().size()) {
                    beta = Math.min(beta, alphaBeta(root.getSuccessor().get(k - 1), alpha, beta));
                    k++;
                }
                return beta;
            }
        }

        this.chooseBoard = root.getActualBoard();
        return root.getHeuristicValue();
    }
    
}
