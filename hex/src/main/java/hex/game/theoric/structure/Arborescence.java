package hex.game.theoric.structure;

import hex.board.Board;
import hex.board.cell.Cell;
import hex.game.player.PlayerName;
import hex.game.theoric.structure.node.ManageNode;
import hex.game.theoric.structure.node.Node;

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

    // COMMANDS

    @Override
    public void parseBoard(PlayerName start) {
        /*Cell[][] grids = this.board.getGrid();
        for (Cell[] cells : grids) {
             for (Cell c : cells) {
                 Board b = this.

             }
        }*/
    }

    public void calculateGainOnAllLeaf() {

    }

    // UTILS


}
