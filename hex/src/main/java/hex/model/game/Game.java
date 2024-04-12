package hex.model.game;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.Shape;
import hex.model.board.cell.State;
import hex.model.game.complementary.Help;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.computer.Computer;
import hex.model.game.player.Player;
import hex.model.game.player.PlayerType;
import hex.model.game.player.computer.Level;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Game implements AbstractGame {

    // ATTRIBUTES

    private final Mode mode;
    private final Board board;
    private final AbstractPlayer[] players;
    private final int size;
    private int currentPlayer;
    private final PropertyChangeSupport pcs;
    private boolean endOfGame;

    private boolean gameWithComputer;

    // CONSTRUCTORS

    public Game(boolean offline, boolean computer, int level, int size) {
        this.mode = offline ? Mode.SAME : Mode.DISTANCE;
        this.gameWithComputer = computer;
        this.size = size;
        this.currentPlayer = 0;
        this.players = initializePlayers(computer, level);
        this.board = createBoard(Shape.HEXAGONAL);
        if (computer) {
            ((Computer) this.players[1]).setGame(this);
        }
        this.pcs = new PropertyChangeSupport(this);
    }

    // REQUESTS

    @Override
    public boolean getIsGameWithComputer() {
        return this.gameWithComputer;
    }

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
    public boolean isEndOfGame() {
        return endOfGame;
    }

    private void isEndOfGameAboutCurrentPlayer() {
        this.endOfGame = this.existLine();
        this.pcs.firePropertyChange(PROP_END_GAME, false, endOfGame);
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

    // COMMANDS

    @Override
    public void takeCell(int i, int j) {
        Cell c = this.board.getGrid()[i][j];
        c.setPlayer(this.getCurrentPlayer());
        c.setState(State.PLAYER);
        if (c.getPlayer().getType() == PlayerType.COMPUTER) {
            this.pcs.firePropertyChange(PROP_TAKE_CELL_BY_COMPUTER, null, c);
        }
    }

    @Override
    public void consumeTurn() {
        this.isEndOfGameAboutCurrentPlayer();
        this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
        this.pcs.firePropertyChange(PROP_CURR_PLAYER_ID, null, this.currentPlayer);
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

    private AbstractPlayer[] initializePlayers(boolean computerPlay, int level) {
        AbstractPlayer[] players = new AbstractPlayer[MAX_NUMBER_OF_PLAYER];
        players[0] = new Player(PlayerType.HUMAN, FIRST_PLAYER);
        if (computerPlay) {
            players[1] = new Computer(SECOND_PLAYER, Level.values()[level]);
        } else {
            players[1] = new Player(PlayerType.HUMAN, SECOND_PLAYER);
        }
        return players;
    }

    private boolean existLine() {
        boolean readOnOrdinate = this.currentPlayer == 0;
        Cell[][] grid = this.board.getGrid();
        if (readOnOrdinate) {
            for (Cell[] cells : grid) {
                Cell c = cells[0];
                c.setVisit(true);
                if (c.getState() != State.EMPTY
                        && c.getPlayer() == this.getCurrentPlayer()) {
                    if (this.canPathFromCell(c)) {
                        return true;
                    } else {
                        this.board.refreshVisit();
                    }
                }
            }
        } else {
            Cell[] line = grid[0];
            for (Cell c : line) {
                if (c.getState() != State.EMPTY
                    && c.getPlayer() == this.getCurrentPlayer()) {
                    c.setVisit(true);
                    if (this.canPathFromCell(c)) {
                        return true;
                    } else {
                        this.board.refreshVisit();
                    }
                }
            }
        }

        return false;
    }

    private boolean canPathFromCell(Cell c) {
        boolean readOnOrdinate = this.currentPlayer == 0;

        if (readOnOrdinate) {
            if (c.getOrdinate() == this.board.getGrid().length - 1) {
                return true;
            }
        } else {
            if (c.getAbscissa() == this.board.getGrid().length - 1) {
                return true;
            }
        }

        for (Direction d : c.getDirections().keySet()) {
            Cell newCell = c.getCellOnDir(d);
            if (newCell.getState() == State.PLAYER
                && newCell.getPlayer() == this.getCurrentPlayer()
                && !newCell.getVisited())
            {
                newCell.setVisit(true);
                return this.canPathFromCell(newCell);
            }
        }
        return false;
    }
}