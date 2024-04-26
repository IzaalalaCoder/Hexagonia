package hex.model.game.theoric.algorithm;

import hex.model.board.cell.Cell;
import hex.model.game.theoric.structure.node.Node;

public interface Theory {

    // REQUESTS

    public Cell getChooseCell();

    // COMMANDS

    public void analyze(Node root);
}
