package hex.model.game.theoric.structure;

import hex.model.board.Board;
import hex.model.game.theoric.structure.node.ManageNode;

public class Arborescence implements Tree {

    // ATTRIBUTES
    private final ManageNode<Board> root;
    private final Board board;

    // CONSTRUCTORS

    public Arborescence(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("");
        }

        this.board = board;
        this.root = null;
    }

    // REQUESTS

    @Override
    public ManageNode<Board> getRoot() {
        return this.root;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void parseBoard() {

    }

    // COMMANDS

    public void calculateGainOnAllLeaf() {

    }

    // UTILS


}
