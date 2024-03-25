package hex.platform.view.shapes.forms;

import hex.board.cell.Direction;
import hex.board.cell.Shape;
import hex.platform.controller.shapes.forms.ControlForms;
import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Map;

public class TriangleBottomButton extends JButton implements Forms {

    private final Shape shape = Shape.SQUARE;
    private MouseAdapter listener;
    private final Map<Direction, Color> borderColor;

    private static final int[] X = {Forms.SIZE_DEFAULT /2, 0, Forms.SIZE_DEFAULT};
    private static final int[] Y = {0, Forms.SIZE_DEFAULT, Forms.SIZE_DEFAULT};

    public TriangleBottomButton() {
        this.borderColor = new HashMap<>();
        this.changeSize();
        this.setContentAreaFilled(false);
        this.listener = new ControlForms(this, null);
        this.addMouseListener(this.listener);
    }

    @Override
    public Shape getShapeOfJButton() {
        return shape;
    }

    @Override
    public boolean isContainedInForm(Point p) {
        Polygon polygon = new Polygon(X, Y, X.length);
        return polygon.contains(p);
    }

    @Override
    public void changeColor(Color c) {
        this.setBackground(c);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillPolygon(X, Y, X.length);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawPolygon(X, Y, X.length);
    }

    @Override
    public void setColorsForBorder(Map<Direction, Color> c) {
        for (Direction d : this.borderColor.keySet()) {
            Color color = this.borderColor.get(d);
            if (!color.equals(c.get(d))) {
                this.borderColor.replace(d, c.get(d));
            }
        }
    }

    @Override
    public Map<Direction, Color> getColorsForBorder() {
        return this.borderColor;
    }

    @Override
    public void removeController() {
        this.removeMouseListener(this.listener);
        this.listener = null;
    }

    // UTILS

    private void changeSize() {
        Dimension size = getPreferredSize();
        size.width = size.height = SIZE_DEFAULT;
        setPreferredSize(size);
    }
}
