package hex.platform.view.shapes.forms;

import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;

public class MainTest {
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

    public static JPanel createHexagonBoardCollapse(int size) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT * size * 20,size  * Forms.SIZE_DEFAULT ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
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
        return p;
    }
    public static JPanel createHexagonBoardNotCollapse(int size) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setPreferredSize(new Dimension(Forms.SIZE_DEFAULT * size * 20,size  * Forms.SIZE_DEFAULT ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(-10, -18, 0, 0);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size ; j++) {
                HexagonButton button = new HexagonButton();
                gbc.gridx = i + j * 2 + 1;
                gbc.gridy = i * 2 + 1;

                gbc.gridwidth =  1;
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

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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


    @SuppressWarnings("unused")
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
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.add(createHexagonBoardNotCollapse(11));
        //f.setPreferredSize(new Dimension(1000, 700));
        f.add(p);
        f.setVisible(true);

        JFrame g = new JFrame();
        JPanel q = new JPanel();
        q.add(createHexagonBoardCollapse(11));
        //f.setPreferredSize(new Dimension(1000, 700));
        g.add(q);
        g.setVisible(true);
    }
}
