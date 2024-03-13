package hex.platform.view.shapes.forms;

import hex.board.cell.Direction;
import hex.board.cell.Shape;
import hex.platform.controller.shapes.forms.ControlForms;
import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

public class SquareButton extends JButton implements Forms  {

    private final Shape shape = Shape.SQUARE;
    private MouseAdapter listener;
    private final Map<Direction, Color> borderColor;

    public SquareButton() {
        this.borderColor = new HashMap<>();
        this.changeSize();
        this.setContentAreaFilled(false);
        this.listener = new ControlForms(this);
        this.addMouseListener(this.listener);

        // Le mieux est de ne pas coller les boutons
    }

    @Override
    public Shape getShapeOfJButton() {
        return shape;
    }

    @Override
    public boolean isContainedInForm(Point p) {
        java.awt.Shape shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        return shape.contains(p.getX(), p.getY());
    }

    @Override
    public String getCoordinate() {
        return null;
    }

    @Override
    public void changeColor(Color c) {
        this.setBackground(c);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
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
    public void changeController() {
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
