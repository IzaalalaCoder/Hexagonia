package hex.model.game;

import hex.model.board.Board;
import hex.model.game.player.AbstractPlayer;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface AbstractGame {

    public final int NUMBER_SIDES = 6;

    public final String PROP_CURR_PLAYER_ID = "currentPlayer";
    public final String PROP_TAKE_CELL_BY_COMPUTER = "takeCell";
    public final String PROP_END_GAME = "EndOfGame";

    boolean getIsGameWithComputer();
    public final int FIRST_PLAYER = 0;
    public final int SECOND_PLAYER = 1;

    public final int MAX_NUMBER_OF_PLAYER = 2;

    /**
     * Return size's board
     * @return int
     */
    int getSize();

    public void setHistoricActions(List<Action> history);
    public AbstractPlayer getComputer();

    /**
     * Return board's game
     * @return Board
     */
    Board getBoard();

    /**
     * Returns all players
     * @return Player[]
     */
    AbstractPlayer[] getPlayers();

    int getPositionCurrentPlayer();

    /**
     * Check if ended game
     * @return boolean
     */
    boolean isEndOfGame();

    void takeCell(int i, int j);

    void setCurrentPlayer(int current);
    /**
     * Return current player
     * @return Player
     */
    AbstractPlayer getCurrentPlayer();

    /**
     * Current player act so consume his turn
     * @post
     *      currentPlayer != this.currentPlayer
     */
    void consumeTurn();

    public List<Action> getHistoricActions();

    public AbstractPlayer getWinner();
    
    /**
     * Game restart play
     */
    void replay();

    PropertyChangeListener[] getPropertyChangeListeners(String pName);

    void addPropertyChangeListener(String pName, PropertyChangeListener pcl);
}
