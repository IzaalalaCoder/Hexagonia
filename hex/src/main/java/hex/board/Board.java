package hex.board;

import hex.board.cell.*;
import hex.board.cell.Shape;

import java.awt.*;

public class Board {
    // Attributs

    private Dimension size;
    private final Cell[][] grid;
    private final Shape shape;

    // Constructeurs

    public Board(int w) {
        this.size = new Dimension(w, w);
        this.shape = Shape.HEXAGONAL;
        this.grid = createGrid();
    }

    public Board(int w, int h, Shape shape) {
        this.size = new Dimension(w, h);
        this.shape = shape;
        this.grid = createGrid();
    }

    public Board(Cell[][] g, Shape shape) {
        this.grid = copyGrid(g);
        this.shape = shape;
    }

    // Requêtes

    public Cell[][] getGrid() {
        //return this.copyGrid(this.grid);
        return this.grid;
    }

    public Shape getShape() { return this.shape;}

    public boolean coordinateIsValid(int i, int j) {
        final int w = this.size.width;
        final int h = this.size.height;
        return !(i < 0 || i >= h ||
                j < 0 || j >= w);
    }

    // Commandes

    public void clearGrid() {
        for (int i = 0; i < this.size.height; i++) {
            for (int j = 0; j < this.size.width; j++) {
                this.grid[i][j].clearCell();
            }
        }
    }

    // Outils

    private Cell[][] copyGrid(Cell[][] grid) {
        Cell[][] cells = new Cell[this.grid.length][];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                cells[i][j] = Cell.copyCell(grid[i][j]);
            }
        }
        return cells;
    }

    private Cell[][] createGrid() {
        final int w = this.size.width;
        final int h = this.size.height;
        Cell[][] cells = new Cell[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cells[i][j] = new Cell(this.shape);
            }
        }

        // Initialization of all direction's cells
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Cell c = cells[i][j];
                for (Direction d : Direction.values()) {
                    Point newCoordinate = d.getNewCoordinates(i, j);
                    if (this.coordinateIsValid(newCoordinate.x, newCoordinate.y)) {
                        Cell newCell = cells[newCoordinate.x][newCoordinate.y];
                        c.setCellOnDirection(d, newCell);
                    }
                }
            }
        }

        return cells;
    }
}