package hex.board.cell;

import java.util.EnumMap;

public class Cell {
    // Attributs

    private State state;
    private final Shape shape;
    private final EnumMap<Direction, Cell> directions;

    // Constructeurs

    public Cell(Shape s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        this.state = State.EMPTY;
        this.shape = s;
        this.directions = new EnumMap<>(Direction.class);
        this.initializeDirections();
    }

    // Requêtes

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
        Cell cell = new Cell(c.getShape());
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

    // Commandes

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
        this.directions.replace(d, c);
    }

    // Outils

    private void initializeDirections() {
        for (Direction d : this.shape.getDirections()) {
            this.directions.put(d, null);
        }
    }

    private static void manageDirections(Cell in, Cell from) {
        for (Direction d : from.getDirections().keySet()) {
            Cell cellOnDirection = from.getDirections().get(d);
            in.getDirections().replace(d, cellOnDirection);
        }
    }
}