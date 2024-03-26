package hex.model.board.cell;

import hex.model.game.player.AbstractPlayer;

import java.util.EnumMap;

public class Cell {

    // ATTRIBUTES

    private State state;
    private final Shape shape;
    private final EnumMap<Direction, Cell> directions;
    private AbstractPlayer player;
    private final int numberOfMemberships;

    // CONSTRUCTOR

    public Cell(Shape s, int players) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        this.state = State.EMPTY;
        this.shape = s;
        this.directions = new EnumMap<>(Direction.class);
        this.player = null;
        this.numberOfMemberships = players;
    }

    // REQUESTS

    public Shape getShape() {
        return shape;
    }

    public State getState() {
        return state;
    }

    public EnumMap<Direction, Cell> getDirections() {
        return this.directions;
    }

    public static Cell copyCell(Cell c) {
        Cell cell = new Cell(c.getShape(), c.numberOfMemberships);
        cell.setState(c.getState());
        manageDirections(cell, c);
        return cell;
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

    public void setPlayer(AbstractPlayer p) {
        if (this.getState() == State.PLAYER) {
            throw new IllegalArgumentException("Invalid argument");
        }
        this.setState(State.PLAYER);
        this.player = p;
    }

    public void setState(State s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        state = s;
    }

    public void setCellOnDirection(Direction d, Cell c) {
        if (d == null || c == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        if (c.equals(this)) {
            throw new IllegalArgumentException("Don't cell correct");
        }
        this.directions.put(d, c);
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
}