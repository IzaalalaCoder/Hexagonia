package hex.platform.view.shapes.forms;

import hex.model.board.cell.Direction;
import hex.model.board.cell.Shape;

import java.awt.event.MouseAdapter;

import hex.model.game.Game;
import hex.platform.controller.shapes.forms.ControlForms;
import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HexagonButton extends JButton implements Forms {

    public static final Shape SHAPE = Shape.HEXAGONAL;

    // ATTRIBUTES

    private MouseAdapter listener;
    private final Map<Direction, Color> borderColor;
    int[] x = new int[SHAPE.getNbSides()];
    int[] y = new int[SHAPE.getNbSides()];
    double angle = 2 * Math.PI / SHAPE.getNbSides();


    // CONSTRUCTOR

    public HexagonButton(Game model, Map<Direction, Color> border) {
        manageCoordinate();
        this.borderColor = border;
        this.changeSize();
        this.setContentAreaFilled(false);
        this.listener = new ControlForms(model);
        this.addMouseListener(this.listener);
    }

    public HexagonButton() {
        manageCoordinate();
        this.borderColor = new HashMap<>();
        this.changeSize();
        this.setContentAreaFilled(false);
        this.listener = new ControlForms(null);
        this.addMouseListener(this.listener);

    }

    @Override
    public Map<Direction, Color> getColorsForBorder() {
        return this.borderColor;
    }

    // REQUESTS

    @Override
    public Shape getShapeOfJButton() {
        return SHAPE;
    }

    @Override
    public boolean isContainedInForm(Point p) {
        Polygon polygon = new Polygon(x, y, SHAPE.getNbSides());
        return polygon.contains(p);
    }

    private void manageCoordinate() {
        for(int i=0; i<SHAPE.getNbSides(); i++) {
            double v = i * angle;
            x[i] = Forms.SIZE_DEFAULT / 2 + (int) Math.round(((double) Forms.SIZE_DEFAULT / 2)
                    * Math.cos(v + Math.PI / 2));
            y[i] = Forms.SIZE_DEFAULT / 2 + (int) Math.round(((double) Forms.SIZE_DEFAULT / 2)
                    * Math.sin(v + Math.PI / 2));
        }
    }

    // COMMANDS

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillPolygon(x, y, SHAPE.getNbSides());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawPolygon(x, y, SHAPE.getNbSides());
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
