package hex.model.game.theoric.algorithm.max;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.LabelPlayer;
import hex.model.game.theoric.structure.node.util.Status;

public class Minimax extends AbstractTheory {

    // CONSTRUCTOR

    public Minimax(Game game, Level level) {
        super(game, level);
        this.analyze(tree.getRoot());
    } 

    // UTILS

    public void analyze(Node root) {
        this.minimax(root);
        this.chooseBoard(root);
    }
    
    private Double minimax(Node root) {
        Double val;
        if (root.getStatus() == Status.LEAF) {
            val = root.getHeuristicValue();
        } else {
            if (root.getLabelPlayer() == LabelPlayer.MAX) {
                val = -700.0;
                for (Node n : root.getSuccessor()) {
                    val = Math.max(val, this.minimax(n));
                }
            } else {
                val = 700.0;
                for (Node n : root.getSuccessor()) {
                    val = Math.min(val, this.minimax(n));
                }
            }
        }
        root.setHeuristicValue(val);
        return val;
    }
}