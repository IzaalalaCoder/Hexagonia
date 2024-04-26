package hex.model.game;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.computer.Computer;
import hex.model.game.player.Player;
import hex.model.game.player.PlayerType;
import hex.model.game.player.computer.Level;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Game implements AbstractGame {

    // ATTRIBUTES

    private final Board board;
    private final AbstractPlayer[] players;
    private AbstractPlayer winner;
    private final int size;
    private int currentPlayer;
    private final PropertyChangeSupport pcs;
    private boolean endOfGame;
    private boolean gameWithComputer;
    private List<Action> historyAction;

    // CONSTRUCTORS

    public Game(boolean computer, int level, int size) {
        this.historyAction = new ArrayList<>();
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
        this.historyAction = new ArrayList<>();
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

    // REQUESTS

    @Override
    public void setHistoricActions(List<Action> history) {
        this.historyAction = history;
    }


    @Override
    public List<Action> getHistoricActions() {
        return this.historyAction;
    }

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
    public AbstractPlayer getWinner() {
        return this.winner;
    }
    
    // COMMANDS

    @Override
    public void takeCell(int i, int j) {
        Cell c = this.board.getGrid()[i][j];
        c.setPlayer(this.getCurrentPlayer());
        this.historyAction.add(new Action(c, this.getCurrentPlayer()));
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

    @Override
    public void setCurrentPlayer(int current) {
        if (current < 0 || current > 1) {
            throw new AssertionError();
        }

        this.currentPlayer = current;
    }

    // UTILS

    private Board createBoard() {
        return new Board(this.size, this.players);
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
        boolean win = false;
        if (readOnOrdinate) {
            for (Cell[] cells : grid) {
                Cell c = cells[0];
                if (c.getState() != State.EMPTY
                        && c.getPlayer() == this.getCurrentPlayer()) {
                    c.setVisit(true);
                    if (this.canPathFromCell(c)) {
                        win = true;
                    } else {
                        this.board.refreshVisit(c.getOrdinate());
                    }
                }
            }
        } else {
            for (int i = 0; i < this.getSize(); i++) {
                Cell c = grid[0][i];
                if (c.getState() != State.EMPTY
                    && c.getPlayer() == this.getCurrentPlayer()) {
                    c.setVisit(true);
                    if (this.canPathFromCell(c)) {
                        win = true;
                    } else {
                        this.board.refreshVisit(c.getOrdinate());
                    }
                }
            }
        }

        if (win) {
            this.winner = this.players[this.currentPlayer];
            return true;
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

            if (newCell != null) {
                if (newCell.getState() == State.PLAYER
                    && newCell.getPlayer() == this.getCurrentPlayer()
                    && !newCell.getVisited())
                {
                    newCell.setVisit(true);
                    
                    if (this.canPathFromCell(newCell)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}