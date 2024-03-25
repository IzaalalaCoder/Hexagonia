package hex.platform.view;

import hex.board.cell.Cell;
import hex.board.cell.Direction;
import hex.board.cell.Shape;
import hex.board.cell.State;
import hex.game.Game;
import hex.platform.view.info.PlayerName;
import hex.platform.view.shapes.Forms;
import hex.platform.view.shapes.forms.HexagonButton;

import javax.swing.*;
import java.awt.*;
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
    }

    // UTILS

    private void displayBoard() {
        JPanel p = new JPanel();
        if (this.model.getBoard().getShape() == Shape.HEXAGONAL) {
            this.createHexBoard(p);
        }
    }

    private void createHexBoard(JPanel p) {
        p.setLayout(new GridBagLayout());
        //p.setPreferredSize(new Dimension());
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



                Map<Direction, Color> colorsForBorder = this.generateBorderColor(c, i, j, cells.length - 1);

                if (c.getState() != State.EMPTY) {
                    this.buttons[i][j].changeColor(
                            PlayerName.values()[c.getPlayer().getPosition()].getDefaultColorForPlayer());
                }

                HexagonButton button = new HexagonButton(model, colorsForBorder);
                this.buttons[i][j] = button;

                p.add(button, gbc);
            }
            i++;
        }
        this.add(p, BorderLayout.CENTER);
    }

    private Map<Direction, Color> generateBorderColor(Cell c, int i, int j, int size) {
        Map<Direction, Color> colorsForBorder = new HashMap<>();
        if (c.getNumberOfMemberships() == 1) {
            if (i == 0) {
                colorsForBorder.put(Direction.TOP_LEFT, PlayerName.SECOND.getDefaultColorForPlayer());
                colorsForBorder.put(Direction.TOP_RIGHT, PlayerName.SECOND.getDefaultColorForPlayer());
            } else if (i == size) {
                colorsForBorder.put(Direction.BOTTOM_LEFT, PlayerName.SECOND.getDefaultColorForPlayer());
                colorsForBorder.put(Direction.BOTTOM_RIGHT, PlayerName.SECOND.getDefaultColorForPlayer());
            } else if (j == 0) {
                colorsForBorder.put(Direction.LEFT, PlayerName.SECOND.getDefaultColorForPlayer());
                colorsForBorder.put(Direction.BOTTOM_LEFT, PlayerName.SECOND.getDefaultColorForPlayer());
            } else if (j == size) {
                colorsForBorder.put(Direction.RIGHT, PlayerName.SECOND.getDefaultColorForPlayer());
                colorsForBorder.put(Direction.TOP_RIGHT, PlayerName.SECOND.getDefaultColorForPlayer());
            }
        } else if (c.getNumberOfMemberships() == 2) {
            if (i == 0) {
                if (j == 0) {

                } else if (j == size) {

                }
            } else if (i == size) {
                if (j == 0) {

                } else if (j == size) {

                }
            }
        }
        return colorsForBorder;
    }

    public void removeAllController() {
        final int size = this.buttons.length;
        for (Forms[] button : this.buttons) {
            for (int j = 0; j < size; j++) {
                button[j].removeController();
            }
        }
    }

}
