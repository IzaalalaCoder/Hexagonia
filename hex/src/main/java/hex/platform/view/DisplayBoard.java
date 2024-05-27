package hex.platform.view;

import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import hex.model.game.Game;
import hex.platform.controller.ControlBoard;
import hex.platform.view.info.PlayerName;
import hex.platform.view.shapes.Forms;
import hex.platform.view.shapes.HexagonButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

public class DisplayBoard extends JPanel {

    // ATTRIBUTES

    private final Game model;
    private final Forms[][] buttons;

    // CONSTRUCTORS

    public DisplayBoard(Game game) {
        this.model = game;
        this.buttons = new Forms[game.getSize()][game.getSize()];
        this.setLayout(new BorderLayout());
        this.displayBoard();
        this.createController();
    }

    // COMMANDS

    public void removeAllController() {
        final int size = this.buttons.length;
        for (Forms[] button : this.buttons) {
            for (int j = 0; j < size; j++) {
                button[j].removeController();
            }
        }
    }

    // UTILS

    private void createController() {
        new ControlBoard(this.buttons, model, this);
        if (model.isEndOfGame()) {
            this.removeAllController();
        }
    }

    private void displayBoard() {
        JPanel p = new JPanel();
        this.createHexBoard(p);
    }

    private Map<Direction, Color> generateBorderColor(int i, int j) {
        Map<Direction, Color> colorsForBorder = new HashMap<>();
        for (Direction d : Direction.values()) {
            colorsForBorder.put(d, Color.BLACK);
        }

        if (i == 0) {
            if (j == 0) {
                colorsForBorder.replace(Direction.LEFT, Color.BLUE);
                colorsForBorder.replace(Direction.BOTTOM_LEFT, Color.BLUE);
                colorsForBorder.replace(Direction.TOP_LEFT, Color.RED);
                colorsForBorder.replace(Direction.TOP_RIGHT, Color.RED);
            } else if (j == this.model.getSize() - 1) {
                colorsForBorder.replace(Direction.RIGHT, Color.BLUE);
                colorsForBorder.replace(Direction.TOP_LEFT, Color.RED);
                colorsForBorder.replace(Direction.TOP_RIGHT, Color.RED);
            } else {
                colorsForBorder.replace(Direction.TOP_LEFT, Color.RED);
                colorsForBorder.replace(Direction.TOP_RIGHT, Color.RED);
            }
        } else if (i == this.model.getSize() - 1) {
            if (j == 0) {
                colorsForBorder.replace(Direction.LEFT, Color.BLUE);
                colorsForBorder.replace(Direction.BOTTOM_LEFT, Color.RED);
                colorsForBorder.replace(Direction.BOTTOM_RIGHT, Color.RED);
            } else if (j == this.model.getSize() - 1) {
                colorsForBorder.replace(Direction.RIGHT, Color.BLUE);
                colorsForBorder.replace(Direction.TOP_RIGHT, Color.BLUE);
                colorsForBorder.replace(Direction.BOTTOM_LEFT, Color.RED);
                colorsForBorder.replace(Direction.BOTTOM_RIGHT, Color.RED);
            } else {
                colorsForBorder.replace(Direction.BOTTOM_LEFT, Color.RED);
                colorsForBorder.replace(Direction.BOTTOM_RIGHT, Color.RED);
            }
        } else {
            if (j == 0) {
                colorsForBorder.replace(Direction.LEFT, Color.BLUE);
                colorsForBorder.replace(Direction.BOTTOM_LEFT, Color.BLUE);
            } else if (j == this.model.getSize() - 1) {
                colorsForBorder.replace(Direction.RIGHT, Color.BLUE);
                colorsForBorder.replace(Direction.TOP_RIGHT, Color.BLUE);
            }
        }
        
        return colorsForBorder;
    }

    private void createHexBoard(JPanel p) {
        p.setLayout(new GridBagLayout());
        Cell[][] grids = model.getBoard().getGrid();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(-13, -28, 0, 0);

        int i = 0;
        for (Cell[] cells : grids) {
            for (int j = 0; j < cells.length ; j++) {
                Cell c = cells[j];


                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;
                gbc.gridwidth =  1;

                Map<Direction, Color> colorsForBorder = this.generateBorderColor(i, j);
                HexagonButton button = new HexagonButton(model, colorsForBorder, i, j);

                this.buttons[i][j] = button;

                if (c.getState() != State.EMPTY) {
                    this.buttons[i][j].changeColor(
                            PlayerName.values()[c.getPlayer().getPosition()].getDefaultColorForPlayer());
                    this.buttons[i][j].removeController();
                } else {
                    this.buttons[i][j].changeColor(Color.WHITE);
                }

                p.add(button, gbc);
            }
            i++;
        }
        this.add(p, BorderLayout.CENTER);
    }
}