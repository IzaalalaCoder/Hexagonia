package hex.model.game.theoric.algorithm.max;

import hex.model.board.cell.Cell;
import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.LabelPlayer;
import hex.model.game.theoric.structure.node.util.Status;
import hex.model.game.theoric.structure.tree.Arborescence;


public class Minimax implements Theory {

    // ATTRIBUTES

    private Cell chooseCell;

    // CONSTRUCTOR

    public Minimax(Game game, Level level) {
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
        this.minimax(root);
    }

    // UTILS

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
