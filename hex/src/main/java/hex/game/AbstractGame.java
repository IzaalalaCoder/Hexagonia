package hex.game;

import hex.board.Board;
import hex.game.complementary.Help;
import hex.game.player.Player;
import hex.util.structure.stack.Stack;

public interface AbstractGame {

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
    Player[] getPlayers();

    /**
     * Check if ended game
     * @return boolean
     */
    boolean isEndOfGame();

    /**
     * Return current player
     * @return Player
     */
    Player getCurrentPlayer();

    /**
     * Return help for Player
     * @return Help
     */
    Help getHelp();

    /**
     * Return action's player history
     * @return Stack<Action>
     */
    Stack<Action> getHistory();

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

    /**
     * Reset his game
     */
    void reset();

}
