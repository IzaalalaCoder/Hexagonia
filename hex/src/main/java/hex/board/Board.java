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
        return this.copyGrid(this.grid);
    }

    // Commandes

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

        return cells;
    }
}