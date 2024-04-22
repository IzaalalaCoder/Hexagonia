package hex.model.game.theoric.algorithm.alphabeta;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.algorithm.tree.Arborescence;
import hex.model.game.theoric.structure.Node;
import hex.model.game.theoric.structure.util.LabelPlayer;
import hex.model.game.theoric.structure.util.Status;

public class AlphaBeta implements Theory {

    // ATTRIBUTES

    private Cell chooseCell;

    // CONSTRUCTOR

    public AlphaBeta(Board board, Level level) {
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
        this.alphaBeta(root, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    // UTILS

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
        return root.getHeuristicValue();
    }
    
}
