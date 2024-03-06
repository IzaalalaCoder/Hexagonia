package hex.platform.view.shapes.forms;

import javax.swing.*;
import java.awt.*;

public class Main {
    @SuppressWarnings("unused")
    public static JPanel createRoundBoard(int size) {
        JPanel p = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                RoundButton button = new RoundButton();
                p.add(button);
            }
        }
        return p;
    }

    public static JPanel createHexagonBoardV1(int size) {
        JPanel p = new JPanel(new GridLayout(size, 0, 0, 0));
        int offset = 0;
        for (int i = 0; i < size; i++) {
            JPanel q = new JPanel(new GridLayout(1, size + offset));
            int o = 0;
            while (o != offset) {
                q.add(new JPanel());
                o++;
            }
            for (int j = 0; j < size; j++) {
                HexagonButton button = new HexagonButton();
                q.add(button);
            }
            p.add(q);
            offset++;
        }
        return p;
    }

    public static JPanel createHexagonBoardV2(int size) {
        int surtaille = size + (size - 1);
        JPanel p = new JPanel(new GridLayout(size,  size + (size - 1), -2, -2));
        int offset = 0;
        for (int i = 0; i < size; i++) {
            int o = 0;
            while (o != offset) {
                p.add(new JPanel());
                o++;
            }
            for (int j = 0; j < size; j++) {
                HexagonButton button = new HexagonButton();
                p.add(button);
            }
            for (int k = size + offset; k < surtaille; k++) {
                p.add(new JPanel());
                o++;
            }
            offset++;
        }
        return p;
    }

    public static JPanel createHexagonBoardV3(int size) {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;

        //

        for (int j = 0; j < size; j++) {
            HexagonButton button = new HexagonButton();
            gbc.gridx = j;
            gbc.gridy = 0;
            p.add(button, gbc);
        }

        //

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                HexagonButton button = new HexagonButton();
                gbc.gridx = i + j - 1;
                gbc.gridy = i ;

                //
                //gbc.gridx -= i + 2;
                gbc.gridwidth =  size - i ;

                //
                p.add(button, gbc);
            }
        }
        return p;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.CYAN);
        int size = 2;
        frame.add(createHexagonBoardV3(size));
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
