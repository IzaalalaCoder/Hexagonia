package hex.platform.view.shapes.forms;

import hex.model.board.cell.Direction;
import java.awt.event.MouseAdapter;
import hex.model.game.Game;
import hex.platform.controller.shapes.forms.ControlForms;
import hex.platform.view.shapes.Forms;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class HexagonButton extends JButton implements Forms {

    // ATTRIBUTES

    private MouseAdapter listener;
    int[] x = new int[6];
    int[] y = new int[6];
    double angle = 2 * Math.PI / 6;
    private int ordinate;
    private int abscissa;
    private Map<Direction, Color> borderColors;

    // CONSTRUCTOR

    public HexagonButton(Game model, Map<Direction, Color> border, int i, int j) {
        manageCoordinate();
        this.abscissa = i;
        this.ordinate = j;
        this.borderColors = border;
        this.listener = new ControlForms(model);
        this.changeSize();
        this.setContentAreaFilled(false);
        this.addMouseListener(this.listener);
    }

    // REQUESTS

    @Override
    public int getOrdinate() {
        return this.ordinate;
    }

    @Override
    public int getAbscissa() {
        return this.abscissa;
    }

    @Override
    public boolean isContainedInForm(Point p) {
        Polygon polygon = new Polygon(x, y, 6);
        return polygon.contains(p);
    }

    // COMMANDS

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillPolygon(x, y, 6);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        for (int i = 0; i < 6; i++) {
            g.setColor(borderColors.get(Direction.values()[i]));
            int x1 = x[i];
            int y1 = y[i];
            int x2 = x[(i + 1) % 6];
            int y2 = y[(i + 1) % 6];
            g.drawLine(x1, y1, x2, y2); 
        }
    }

    @Override
    public void changeColor(Color c) {
        this.setBackground(c);
    }

    @Override
    public void removeController() {
        this.removeMouseListener(this.listener);
        this.listener = null;
    }

    // UTILS

    private void manageCoordinate() {
        for(int i = 0; i < 6; i++) {
            double v = i * angle;
            x[i] = Forms.SIZE_DEFAULT / 2 + (int) Math.round(((double) Forms.SIZE_DEFAULT / 2)
                    * Math.cos(v + Math.PI / 2));
            y[i] = Forms.SIZE_DEFAULT / 2 + (int) Math.round(((double) Forms.SIZE_DEFAULT / 2)
                    * Math.sin(v + Math.PI / 2));
        }
    }

    private void changeSize() {
        Dimension size = getPreferredSize();
        size.width = size.height = SIZE_DEFAULT;
        setPreferredSize(size);
    }

}
