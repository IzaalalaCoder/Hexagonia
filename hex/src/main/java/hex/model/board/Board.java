package hex.model.board;

import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.game.player.AbstractPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    // ATTRIBUTES

    private final int size;
    private final Cell[][] grid;
    private final List<AbstractPlayer> players;
    private final AbstractPlayer[] abstractPlayer;

    // CONSTRUCTORS

    public Board(int size, AbstractPlayer[] players) {
        this.size = size;
        this.abstractPlayer = players;
        this.players = Arrays.asList(players);
        this.grid = createGrid();
    }

    // REQUESTS

    public Cell[][] getGrid() {
        //return this.copyGrid(this.grid);
        return this.grid;
    }

    public boolean coordinateIsValid(int i, int j) {
        return !(i < 0 || i >= size||
                j < 0 || j >= size);
    }

    public AbstractPlayer[] getAbstractPlayers() {
        return this.abstractPlayer;
    }

    // COMMANDS

    public void clearGrid() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.grid[i][j].clearCell();
            }
        }
    }

    public void refreshVisit() {
        for (Cell[] cells : this.grid) {
            for (Cell c : cells) {
                c.setVisit(false);
            }
        }
    }

    // UTILS

    /*private Cell[][] copyGrid(Cell[][] grid) {
        Cell[][] cells = new Cell[this.grid.length][];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                cells[i][j] = Cell.copyCell(grid[i][j]);
            }
        }
        return cells;
    }*/

    private Cell[][] createGrid() {
        Cell[][] cells = new Cell[size][size];

        List<AbstractPlayer> playersCell = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == 0 || j == size - 1) {
                    playersCell.add(this.players.get(0));
                }

                if (i == 0 || i == size - 1) {
                    playersCell.add(this.players.get(1));
                }
                cells[i][j] = new Cell(playersCell.size(), i, j);
                playersCell.clear();
            }

        }

        // Initialization of all direction's cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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