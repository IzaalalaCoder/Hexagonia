package hex.model.board.cell;

import hex.model.game.player.AbstractPlayer;

import java.util.EnumMap;

public class Cell {

    // ATTRIBUTES

    private State state;
    private EnumMap<Direction, Cell> directions;
    private AbstractPlayer player;
    private boolean visit;
    private final int abscissa;
    private final int ordinate;
    private final int numberOfMemberships;

    // CONSTRUCTOR

    public Cell(int players, int i, int j) {
        this.initializeDirection();
        this.state = State.EMPTY;
        this.abscissa = i;
        this.ordinate = j;
        this.visit = false;
        this.player = null;
        this.numberOfMemberships = players;
    }

    // REQUESTS


    public int getAbscissa() {
        return abscissa;
    }

    public int getOrdinate() {
        return ordinate;
    }

    public boolean getVisited() {
        return visit;
    }

    public State getState() {
        return state;
    }

    public EnumMap<Direction, Cell> getDirections() {
        return this.directions;
    }

    public Cell copyCell() {
        Cell newCell = new Cell(this.numberOfMemberships, this.abscissa, this.ordinate);
        newCell.state = state;
        newCell.player = player;
        newCell.setVisit(this.getVisited());
        manageDirections(newCell, this);
        return newCell;
    }

    public Cell getCellOnDir(Direction d) {
        if (d == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        if (!this.directions.containsKey(d)) {
            throw new IllegalArgumentException("Invalid argument");
        }
        return this.directions.get(d);
    }

    public AbstractPlayer getPlayer() {
        return player;
    }

    public int getNumberOfMemberships() {
        return numberOfMemberships;
    }

    // COMMANDS

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public void setPlayer(AbstractPlayer p) {
        if (this.getState() == State.PLAYER) {
            throw new IllegalArgumentException("Invalid argument");
        }
        state = State.PLAYER;
        this.player = p;
    }

    public void unsetPlayer() {
        if (this.getState() == State.EMPTY) {
            throw new IllegalArgumentException("Invalid argument");
        }
        state = State.EMPTY;
        this.player = null;
    }

    public void setCellOnDirection(Direction d, Cell c) {
        if (d == null || c == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        if (!this.directions.containsKey(d)) {
            throw new IllegalArgumentException("Invalid argument");
        }
        if (c.equals(this)) {
            throw new IllegalArgumentException("Don't cell correct");
        }
        this.directions.replace(d, c);
    }

    public void clearCell() {
        this.state = State.EMPTY;
        this.player = null;
    }

    // UTILS

    private static void manageDirections(Cell in, Cell from) {
        for (Direction d : from.getDirections().keySet()) {
            Cell cellOnDirection = from.getDirections().get(d);
            in.getDirections().replace(d, cellOnDirection);
        }
    }

    private void initializeDirection() {
        this.directions = new EnumMap<>(Direction.class);

        for (Direction d : Direction.values()) {
            this.directions.put(d, null);
        }
    }
}