package hex.model.game.theoric.algorithm;

import hex.model.board.cell.Cell;

public interface Theory {

    public Cell getChooseCell();

    public void readBoardGame();

    public void analyze();
}
