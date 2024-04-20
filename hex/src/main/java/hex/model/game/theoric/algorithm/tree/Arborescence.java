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
    private Board board;
    private int currentPlayer;


    // CONSTRUCTORS
    
    public Arborescence(Board board) {
        this.root = new Node(LabelPlayer.MAX, board);
        this.board = board;
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
        for (Cell[] cells : node.getActualBoard().getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    this.placeFakePlayer(c, board.getAbstractPlayers()[currentPlayer]);
                    Node newNode = new Node(this.currentPlayer == 1 ? LabelPlayer.MIN : LabelPlayer.MAX, node.getActualBoard());
                    node.addSuccessor(newNode);
                    this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
                    this.resetCell(c);
                }
            }
        }
    }

    private void placeFakePlayer(Cell c, AbstractPlayer player) {
        c.setPlayer(player);
        c.setState(State.PLAYER);
    }

    private void resetCell(Cell c) {
        c.setState(State.EMPTY);
        c.setPlayer(null);
    }
}
