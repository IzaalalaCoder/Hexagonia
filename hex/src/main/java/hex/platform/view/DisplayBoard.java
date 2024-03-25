package hex.platform.view;

import hex.board.Board;

import hex.board.cell.Shape;
import hex.game.Game;
import hex.platform.view.shapes.Forms;
import hex.platform.view.shapes.forms.HexagonButton;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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
        final int size = model.getSize();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(-13, -28, 0, 0);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size ; j++) {
                HexagonButton button = new HexagonButton(model);
                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;
                gbc.gridwidth =  1;
                this.buttons[i][j] = button;
                p.add(button, gbc);
            }
        }
        this.add(p, BorderLayout.CENTER);
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
