package hex.model.game.theoric.algorithm.max;

import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.LabelPlayer;
import hex.model.game.theoric.structure.node.util.Status;
import hex.model.game.theoric.structure.tree.Arborescence;


public class Minimax extends AbstractTheory {

    // CONSTRUCTOR

    public Minimax(Game game, Level level) {
        super(game, level);
        Arborescence a = new Arborescence(game);
        this.analyze(a.createArborescence(level));
    } 

    // UTILS

    public void analyze(Node root) {
        this.minimax(root);this.chooseBoard(root);
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
    
    private Double minimax(Node root) {
        Double val;
        if (root.getStatus() == Status.LEAF) {
            val = root.getHeuristicValue();
        } else {
            if (root.getLabelPlayer() == LabelPlayer.MAX) {
                val = Double.MIN_VALUE;
                for (Node n : root.getSuccessor()) {
                    val = Math.max(val, this.minimax(n));
                }
            } else {
                val = Double.MAX_VALUE;
                for (Node n : root.getSuccessor()) {
                    val = Math.min(val, this.minimax(n));
                }
            }
        }
        return val;
    }
    
}
