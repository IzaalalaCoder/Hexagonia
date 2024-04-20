package hex.model.game.theoric.algorithm;

import hex.model.board.Board;
import hex.model.board.cell.Cell;

public interface Theory {

    public Cell getChooseCell();
    
    public void readBoardGame(Board game);

    public void analyze();
}
