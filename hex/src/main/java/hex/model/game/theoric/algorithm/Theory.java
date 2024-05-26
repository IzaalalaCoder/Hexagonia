package hex.model.game.theoric.algorithm;

import hex.model.board.cell.Cell;
import hex.model.game.theoric.structure.tree.Tree;

public interface Theory {

    // CONSTANTS

    double MAX_HEURISTIC_VALUE = 50000.0;
    double MIN_HEURISTIC_VALUE = -50000.0;

    // REQUESTS

    Cell getCell();
}