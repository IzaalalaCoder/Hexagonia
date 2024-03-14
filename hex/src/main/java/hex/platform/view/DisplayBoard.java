package hex.platform.view;

import hex.board.Board;
import hex.platform.view.shapes.forms.HexagonButton;

import javax.swing.*;
import java.awt.*;

public class DisplayBoard extends JPanel {

    // ATTRIBUTES

    private Board model;

    // CONSTRUCTORS

    public DisplayBoard(Board b) {
        this.model = b;
        this.setLayout(new BorderLayout());
        this.displayBoard();
        this.setBackground(Color.BLACK);
    }

    // UTILS

    private void displayBoard() {
        JPanel p = new JPanel();
        switch (this.model.getShape()) {
            case HEXAGONAL -> {
                this.createHexBoard(p);
            }
            default -> {
            }
        }
    }

    private void createHexBoard(JPanel p) {
        p.setLayout(new GridBagLayout());
        //p.setPreferredSize(new Dimension());
        final int size = model.getGrid().length;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(-13, -28, 0, 0);

        //p.setPreferredSize(new Dimension(800, 400 ));
        p.setBackground(Color.blue);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size ; j++) {
                HexagonButton button = new HexagonButton();
                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;
                gbc.gridwidth =  1;
                p.add(button, gbc);
            }
        }
        this.add(p, BorderLayout.CENTER);
    }
}
