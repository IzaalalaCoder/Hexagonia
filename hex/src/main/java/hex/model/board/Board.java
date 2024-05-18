package hex.model.board;

import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.game.player.AbstractPlayer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {

    // ATTRIBUTES

    private final int size;
    private final Cell[][] grid;
    private final AbstractPlayer[] players;

    // CONSTRUCTORS

    public Board(int size, AbstractPlayer[] players) {
        this.size = size;
        this.players = players;
        this.grid = createGrid();
    }

    // REQUESTS

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (Cell[] cells : this.grid) {
            for (Cell c : cells) {
                message.append(c.toString());
            }
            message.append("\n");
        }
        return message.toString();
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    public boolean coordinateIsValid(int i, int j) {
        return !(i < 0 || i >= size || j < 0 || j >= size);
    }

    public AbstractPlayer[] getAbstractPlayers() {
        return this.players;
    }

    public Board getCopy() {
        Board newBoard = new Board(this.getGrid().length, this.getAbstractPlayers());
        for (Cell[] cells : this.getGrid()) {
            for (Cell c : cells) {
                int i = c.getAbscissa();
                int j = c.getOrdinate();
                newBoard.getGrid()[i][j] = c.copyCell();
            }
        }
        newBoard.refreshAllVisit();
        return newBoard;
    }

    // COMMANDS

    public void refreshAllVisit() {
        for (Cell[] cells : this.grid) {
            for (Cell c : cells) {
                c.setVisit(false);
            }
        }
    }

    public void refreshVisit(int y) {
        for (Cell[] cells : this.grid) {
            for (Cell c : cells) {
                if (c.getAbscissa() == 0 || c.getOrdinate() > y) {
                    c.setVisit(false);
                } else if (c.getAbscissa() != 0) {
                    c.setVisit(false);
                }
            }
        }
    }

    // UTILS

    private Cell[][] createGrid() {
        Cell[][] cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
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