package hex.model.board;

import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.game.player.AbstractPlayer;
import java.awt.Point;
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

    public void refreshAllVisit() {
        for (Cell[] cells : this.grid) {
            for (Cell c : cells) {
                c.setVisit(false);
            }
        }
    }

    public boolean coordinateIsValid(int i, int j) {
        return !(i < 0 || i >= size || j < 0 || j >= size);
    }

    public AbstractPlayer[] getAbstractPlayers() {
        return this.abstractPlayer;
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