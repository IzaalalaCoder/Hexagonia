package hex.model.game.theoric.algorithm;

import hex.model.board.cell.Cell;
import hex.model.game.theoric.structure.tree.Tree;

public interface Theory {

    // REQUESTS

    Cell getCell();

    Tree getTree();
}