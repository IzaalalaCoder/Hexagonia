package hex.game.theoric.structure;

import hex.board.Board;
import hex.game.theoric.structure.node.ManageNode;

public interface Tree {

    // REQUEST

    /**
     * Retourne l'arborescence associé au plateau courant
     * La valeur représentatif de l'arborescence est le plateau (pour le moment)
     */
    ManageNode<Board> getRoot();

    Board getBoard();

    // COMMANDS

    /**
     * Read grid and full tree
     */
    void parseBoard();

    void calculateGainOnAllLeaf();
}
