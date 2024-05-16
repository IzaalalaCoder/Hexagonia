package hex.model.game;

import hex.model.board.Board;
import hex.model.game.player.AbstractPlayer;
import java.beans.PropertyChangeListener;
import java.util.List;

public interface AbstractGame {

    String PROP_CURR_PLAYER_ID = "currentPlayer";
    String PROP_TAKE_CELL_BY_COMPUTER = "takeCell";
    String PROP_END_GAME = "EndOfGame";
    int FIRST_PLAYER = 0;
    int SECOND_PLAYER = 1;
    int MAX_NUMBER_OF_PLAYER = 2;

    // REQUESTS

    /**
     * Return size's board
     * @return int
     */
    int getSize();

    int getPositionCurrentPlayer();

    boolean getIsGameWithComputer();

    boolean existLine(int current);

    /**
     * Check if ended game
     * @return boolean
     */
    boolean isEndOfGame();

    List<Action> getHistoricActions();

    AbstractPlayer getWinner();

    AbstractPlayer getComputer();

    /**
     * Return current player
     * @return Player
     */
    AbstractPlayer getCurrentPlayer();

    /**
     * Returns all players
     * @return Player[]
     */
    AbstractPlayer[] getPlayers();

    /**
     * Return board's game
     * @return Board
     */
    Board getBoard();

    PropertyChangeListener[] getPropertyChangeListeners(String pName);

    // COMMANDS

    void setHistoricActions(List<Action> history);

    void setBoard(Board board);

    void takeCell(int i, int j);

    void setCurrentPlayer(int current);

    /**
     * Current player act so consume his turn
     * @post
     *      currentPlayer != this.currentPlayer
     */
    void consumeTurn();

    void addPropertyChangeListener(String pName, PropertyChangeListener pcl);
}
