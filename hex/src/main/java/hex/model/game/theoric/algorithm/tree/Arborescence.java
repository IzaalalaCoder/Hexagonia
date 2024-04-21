package hex.model.game.theoric.algorithm.tree;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.structure.Node;
import hex.model.game.theoric.structure.util.LabelPlayer;

public class Arborescence {

    // ATTRIBUTES

    private Node root;
    private int currentPlayer;


    // CONSTRUCTORS
    
    public Arborescence(Board board) {
        this.root = new Node(LabelPlayer.MAX, board);
        this.currentPlayer = 1;
    }

    // REQUESTS

    public Node createArborescence(Level level) {
        int sizeTail = level.getSizeTail();
        this.buildTree(root, sizeTail);
        return root;
    }

    // UTILS

    private void buildTree(Node node, int tail) {
        if (tail == 0) {
            return;
        }
        Board board = node.getActualBoard().getCopy();
        AbstractPlayer p = board.getAbstractPlayers()[currentPlayer];
        for (Cell[] cells : board.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    c.setPlayer(p);
                    Node newNode = new Node(this.currentPlayer == 1 ? LabelPlayer.MIN : LabelPlayer.MAX, board.getCopy());
                    this.buildTree(newNode, tail - 1);
                    node.addSuccessor(newNode);
                    //this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
                    c.unsetPlayer();
                }
            }
        }
    }
}
