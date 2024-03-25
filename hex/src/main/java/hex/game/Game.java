package hex.game;

import hex.board.*;
import hex.board.cell.*;
import hex.board.cell.Shape;
import hex.game.complementary.Help;
import hex.game.player.AbstractPlayer;
import hex.game.player.computer.Computer;
import hex.game.player.Player;
import hex.game.player.PlayerType;
import hex.util.structure.stack.ManageStack;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Game implements AbstractGame {

    // ATTRIBUTES

    private final Mode mode;
    private final Board board;
    private final AbstractPlayer[] players;
    private final int size;
    private int currentPlayer;
    private ManageStack<Action> history;
    private final PropertyChangeSupport pcs;

    // CONSTRUCTORS

    public Game(boolean offline, boolean computer, int level, int size) {
        this.mode = offline ? Mode.SAME : Mode.DISTANCE;
        this.size = size;
        this.players = initializePlayers(computer);
        this.currentPlayer = 0;
        this.board = createBoard(Shape.HEXAGONAL);
        this.pcs = new PropertyChangeSupport(this);
    }

    // REQUESTS

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Mode getMode() {
        return mode;
    }

    @Override
    public AbstractPlayer[] getPlayers() {
        return players;
    }

    @Override
    public boolean isEndOfGame(Cell c) {
        return false;
    }

    @Override
    public AbstractPlayer getCurrentPlayer() {
        return this.players[currentPlayer];
    }

    @Override
    public int getPositionCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Help getHelp() {
        return null;
    }

    @Override
    public ManageStack<Action> getHistory() {
        return this.history;
    }

    // COMMANDS

    @Override
    public void consumeTurn() {
        this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
        this.pcs.firePropertyChange(PROP_CURR_PLAYER, false, this.currentPlayer);
    }

    @Override
    public void replay() {
        this.board.clearGrid();
    }

    @Override
    public PropertyChangeListener[] getPropertyChangeListeners(String pName) {
        if (pName == null) {
            throw new AssertionError();
        }
        return pcs.getPropertyChangeListeners(pName);
    }

    @Override
    public void addPropertyChangeListener(String pName, PropertyChangeListener pcl) {
        if (pName == null) {
            throw new AssertionError();
        }
        this.pcs.addPropertyChangeListener(pName, pcl);
    }

    // UTILS

    private Board createBoard(Shape shape) {
        return new Board(this.size, shape, this.players);
    }

    private AbstractPlayer[] initializePlayers(boolean computerPlay) {
        AbstractPlayer[] players = new AbstractPlayer[Game.MAX_NUMBER_OF_PLAYER];
        players[0] = new Player(PlayerType.HUMAN, FIRST_PLAYER);
        if (computerPlay) {
            players[1] = new Computer(SECOND_PLAYER);
        } else {
            players[1] = new Player(PlayerType.HUMAN, SECOND_PLAYER);
        }
        return players;
    }
}