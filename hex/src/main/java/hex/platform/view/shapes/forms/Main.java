package hex.platform.view.shapes.forms;

import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;

import hex.platform.view.menu.MainMenu;

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

    @SuppressWarnings("unused")
    public static JPanel createRoundBoardDe(int size) {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, -20, 0, 0);

        for (int j = 0; j < size; j++) {
            JPanel q = new JPanel();
            gbc.gridx = j * 2;
            gbc.gridy = 0;
            q.setVisible(true);
            q.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT, Forms.SIZE_DEFAULT));
            p.add(q, gbc);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                RoundButton button = new RoundButton();
                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;

                gbc.gridwidth = 1;
            /*gbc.gridwidth =  size - i ;
            if (i != size - 1) {
                gbc.gridwidth /= 2;
            }*/
                //
                p.add(button, gbc);
            }
        }
        return p;
    }

    @SuppressWarnings("unused")
    public static JPanel createHexagonBoard(int size) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT * size * 20,size  * Forms.SIZE_DEFAULT ));
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
        } gbc.insets = new Insets(-13, -28, 0, 0);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size ; j++) {
                if (i != 0) {

                }
                HexagonButton button = new HexagonButton();
                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;

                gbc.gridwidth =  1;
            /*gbc.gridwidth =  size - i ;
            if (i != size - 1) {
                gbc.gridwidth /= 2;
            }*/
                //
                p.add(button, gbc);
            }
        }
        return p;
    }

    @SuppressWarnings("unused")
    public static JPanel createSquareBoardDe(int size) {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, -20, 0, 0);

        for (int j = 0; j < size; j++) {
            JPanel q = new JPanel();
            gbc.gridx = j * 2;
            gbc.gridy = 0;
            q.setVisible(true);
            q.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT, Forms.SIZE_DEFAULT));
            p.add(q, gbc);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SquareButton button = new SquareButton();
                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;

                gbc.gridwidth = 1;
            /*gbc.gridwidth =  size - i ;
            if (i != size - 1) {
                gbc.gridwidth /= 2;
            }*/
                //
                p.add(button, gbc);
            }
        }
        return p;
    }

    public static JPanel createTriangleBottomBoard(int size) {
        JPanel p = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                TriangleBottomButton button = new TriangleBottomButton();
                p.add(button);
            }
        }
        return p;
    }

    public static JPanel createTriangleTopBoard(int size) {
        JPanel p = new JPanel(new GridLayout(size, size, 3, 3));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                TriangleTopButton button = new TriangleTopButton();
                p.add(button);
            }
        }
        return p;
    }

    public static JPanel createTriasssngfffleTopBoard(int size) {
        JPanel p = new JPanel(new GridLayout(size, size, 2, 2));
        //p.setBackground(Color.PINK);
        boolean isBottom = false;
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < i; k++) {
                p.add(new JPanel());
            }
            for (int j = i; j < size; j++) {
                JButton button;
                if (isBottom) {
                    button = new TriangleTopButton();
                } else {
                    button = new TriangleBottomButton();
                }
                p.add(button);
                isBottom = !isBottom;
            }
            isBottom = false;
        }
        return p;
    }


    public static JPanel createDeBoardTriangles(int size) {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        for (int j = 0; j < size; j++) {
            JPanel q = new JPanel();
            gbc.gridx = j * 2;
            gbc.gridy = 0;
            q.setVisible(true);
            q.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT, Forms.SIZE_DEFAULT));
            p.add(q, gbc);
        }


        boolean isBottom = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                gbc.insets = new Insets(1, -80, 0, size - 1  == j ? 0 :-70);
                JButton button;
                if (isBottom) {

                    button = new TriangleBottomButton();
                } else {

                    button = new TriangleTopButton();
                }

                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;
                gbc.gridwidth = 1;
            /*gbc.gridwidth =  size - i ;
            if (i != size - 1) {
                gbc.gridwidth /= 2;
            }*/
                //
                p.add(button, gbc);
                isBottom = !isBottom;
            }
            isBottom = false;
        }
        return p;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setJMenuBar(new MainMenu());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.CYAN);
        int size = 11;
        frame.add(createHexagonBoard(size));
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
