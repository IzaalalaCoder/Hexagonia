package hex.model.game;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.Human;
import hex.model.game.player.computer.Computer;
import hex.model.game.player.PlayerType;
import hex.model.game.player.computer.Level;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Game implements AbstractGame {

    // ATTRIBUTES

    private final AbstractPlayer[] players;
    private final int size;
    private final boolean gameWithComputer;
    private final PropertyChangeSupport pcs;
    private Board board;
    private AbstractPlayer winner;
    private int currentPlayer;
    private boolean endOfGame;

    // CONSTRUCTORS

    public Game(boolean computer, int level, int size) {
        this.gameWithComputer = computer;
        this.size = size;
        this.currentPlayer = 0;
        this.players = initializePlayers(computer, level);
        this.board = createBoard();
        this.endOfGame = false;
        this.winner = null;
        if (computer) {
            ((Computer) this.players[1]).startPlay(this);
        }
        this.pcs = new PropertyChangeSupport(this);
    }

    public Game(boolean endGame, boolean computer, int level, int size) {
        this.gameWithComputer = computer;
        this.size = size;
        this.endOfGame = endGame;
        this.currentPlayer = 0;
        this.winner = null;
        this.players = initializePlayers(computer, level);
        this.board = createBoard();
        if (computer) {
            ((Computer) this.players[1]).startPlay(this);
        }
        this.pcs = new PropertyChangeSupport(this);
    }

    public Game(Board board) {
        this.gameWithComputer = true;
        this.size = board.getGrid().length;
        this.endOfGame = false;
        this.currentPlayer = 0;
        this.winner = null;
        this.players = board.getAbstractPlayers();
        this.board = board;
        this.pcs = new PropertyChangeSupport(this);
    }

    // REQUESTS

    @Override
    public boolean getIsGameWithComputer() {
        return this.gameWithComputer;
    }

    @Override
    public AbstractPlayer getComputer() {
        for (AbstractPlayer p : this.players) {
            if (p.getType() == PlayerType.COMPUTER) {
                return p;
            }
        }
        return null;
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
    public AbstractPlayer[] getPlayers() {
        return players;
    }

    @Override
    public boolean isEndOfGame() {
        return endOfGame;
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
    public AbstractPlayer getWinner() {
        return this.winner;
    }
    
    // COMMANDS

    @Override
    public void takeCell(int i, int j) {
        Cell c = this.board.getGrid()[i][j];
        c.setPlayer(this.getCurrentPlayer());
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

    @Override
    public void setCurrentPlayer(int current) {
        if (current < 0 || current > 1) {
            throw new AssertionError();
        }
        this.currentPlayer = current;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean existLine(int current) {
        this.board.refreshAllVisit();
        boolean readOnOrdinate = current == 0;
        Cell[][] grid = this.board.getGrid();
        boolean win = false;
        if (readOnOrdinate) {
            for (Cell[] cells : grid) {
                Cell c = cells[0];
                if (this.browseCell(c, current)) {
                    win = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < this.getSize(); i++) {
                Cell c = grid[0][i];
                if (this.browseCell(c, current)) {
                    win = true;
                    break;
                }
            }
        }
        if (win) {
            this.winner = this.players[current];
            return true;
        }
        return false;
    }

    // UTILS

    private Board createBoard() {
        return new Board(this.size, this.players);
    }

    private AbstractPlayer[] initializePlayers(boolean computerPlay, int level) {
        AbstractPlayer[] players = new AbstractPlayer[MAX_NUMBER_OF_PLAYER];
        players[0] = new Human(PlayerType.HUMAN, FIRST_PLAYER);
        if (computerPlay) {
            players[1] = new Computer(SECOND_PLAYER, Level.values()[level]);
        } else {
            players[1] = new Human(PlayerType.HUMAN, SECOND_PLAYER);
        }
        return players;
    }

    private boolean browseCell(Cell c, int current) {
        if (c.getState() != State.EMPTY
                && c.getPlayer() == players[current]) {
            c.setVisit(true);
            if (this.canPathFromCell(c, current)) {
                return true;
            } else {
                this.board.refreshVisit(c.getOrdinate());
            }
        }
        return false;
    }

    private boolean canPathFromCell(Cell c, int current) {
        boolean readOnOrdinate = current == 0;
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
            if (newCell != null) {
                if (newCell.getState() == State.PLAYER
                    && newCell.getPlayer() == players[current]
                    && !newCell.isVisited()) {
                    newCell.setVisit(true);
                    if (this.canPathFromCell(newCell, current)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void isEndOfGameAboutCurrentPlayer() {
        this.endOfGame = this.existLine(this.currentPlayer);
        this.pcs.firePropertyChange(PROP_END_GAME, false, endOfGame);
    }
}