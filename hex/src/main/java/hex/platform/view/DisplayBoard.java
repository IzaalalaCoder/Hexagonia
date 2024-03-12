package hex.platform.view;

import hex.board.cell.Cell;
import hex.game.Game;
import hex.platform.view.shapes.Forms;
import hex.platform.view.shapes.forms.HexagonButton;

import javax.swing.*;
import java.awt.*;

public class DisplayBoard extends JPanel {

    // ATTRIBUTES

    private Game model;

    // CONSTRUCTORS

    public DisplayBoard() {
        this.model = new Game();
        this.displayBoard();
    }

    // UTILS

    private void displayBoard() {
        JPanel p = new JPanel();
        switch (this.model.getBoard().getShape()) {
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
        p.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT * size * 2,size  * Forms.SIZE_DEFAULT ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,-28,14, 0);

        for (int j = 0; j < size; j++) {
            JPanel q = new JPanel();
            gbc.gridx = j * 2;
            gbc.gridy = 0;
            q.setVisible(true);
            q.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT -1, Forms.SIZE_DEFAULT- 1));
            p.add(q, gbc);
        }

        gbc.insets = new Insets(-13, -28, 0, 0);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size ; j++) {
                HexagonButton button = new HexagonButton();
                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;
                gbc.gridwidth =  1;
                p.add(button, gbc);
            }
        }
    }

}
