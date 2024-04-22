package hex.platform.view;

import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import hex.model.game.Game;
import hex.platform.controller.ControlBoard;
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
        this.createController();
    }

    // UTILS

    private void createController() {
        new ControlBoard(this.buttons, model, this);
    }

    private void displayBoard() {
        JPanel p = new JPanel();
        this.createHexBoard(p);
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
                HexagonButton button = new HexagonButton(model, colorsForBorder, i, j);

                this.buttons[i][j] = button;

                if (c.getState() != State.EMPTY) {
                    this.buttons[i][j].changeColor(
                            PlayerName.values()[c.getPlayer().getPosition()].getDefaultColorForPlayer());
                } else {
                    this.buttons[i][j].changeColor(Color.WHITE);
                }

                p.add(button, gbc);
            }
            i++;
        }
        this.add(p, BorderLayout.CENTER);
    }

    private Map<Direction, Color> generateBorderColor(Cell c, int i, int j, int size) {
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
