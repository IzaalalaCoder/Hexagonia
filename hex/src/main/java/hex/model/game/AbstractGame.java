package hex.model.game;

import hex.model.board.Board;
import hex.model.game.complementary.Help;
import hex.model.game.player.AbstractPlayer;

import java.beans.PropertyChangeListener;

public interface AbstractGame {

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

    /**
     * Return current player
     * @return Player
     */
    AbstractPlayer getCurrentPlayer();

    /**
     * Return help for Player
     * @return Help
     */
    Help getHelp();

    /**
     * Current player act so consume his turn
     * @post
     *      currentPlayer != this.currentPlayer
     */
    void consumeTurn();

    /**
     * Game restart play
     */
    void replay();

    PropertyChangeListener[] getPropertyChangeListeners(String pName);

    void addPropertyChangeListener(String pName, PropertyChangeListener pcl);
}
