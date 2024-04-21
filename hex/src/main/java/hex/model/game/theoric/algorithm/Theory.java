package hex.model.game.theoric.algorithm;

import hex.model.board.cell.Cell;
import hex.model.game.theoric.structure.Node;

public interface Theory {

    public Cell getChooseCell();

    public void analyze(Node root);
}
