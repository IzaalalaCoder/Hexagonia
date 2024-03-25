package hex.platform.view.shapes.forms;

import hex.board.cell.Direction;
import hex.board.cell.Shape;

import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;

import hex.platform.controller.shapes.forms.ControlForms;
import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RoundButton extends JButton implements Forms {

    public static final Shape SHAPE = Shape.CIRCLE;

    // ATTRIBUTES

    private MouseAdapter listener;
    private final Map<Direction, Color> borderColor;

    // CONSTRUCTOR

    public RoundButton() {
        this.borderColor = new HashMap<>();
        this.changeSize();
        this.setContentAreaFilled(false);
        this.listener = new ControlForms(this, null);
        this.addMouseListener(this.listener);
    }

    // REQUESTS

    @Override
    public Shape getShapeOfJButton() {
        return SHAPE;
    }

    @Override
    public boolean isContainedInForm(Point p) {
        java.awt.Shape shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return shape.contains(p);
    }

    @Override
    public String getCoordinate() {
        return null;
    }

    // COMMANDS

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillOval(0,0,getSize().width - 1,getSize().height - 1);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
    }

    @Override
    public void changeColor(Color c) {
        this.setBackground(c);
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
