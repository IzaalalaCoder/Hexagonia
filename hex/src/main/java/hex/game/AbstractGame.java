package hex.game;

import hex.board.Board;
import hex.board.cell.Cell;
import hex.game.complementary.Help;
import hex.game.player.AbstractPlayer;
import hex.util.structure.stack.ManageStack;
import hex.util.structure.stack.Stack;

import java.beans.PropertyChangeListener;

public interface AbstractGame {

    public final String PROP_CURR_PLAYER = "currentPlayer";

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
     * Return mode's play game
     * @return Mode
     */
    Mode getMode();

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
    boolean isEndOfGame(Cell c);

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
     * Return action's player history
     * @return Stack<Action>
     */
    ManageStack<Action> getHistory();

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
