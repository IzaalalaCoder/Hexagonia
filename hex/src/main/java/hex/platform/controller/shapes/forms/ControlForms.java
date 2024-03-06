package hex.platform.controller.shapes.forms;

import hex.game.player.PlayerName;
import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlForms extends MouseAdapter {

    // CONSTRUCTOR

    public ControlForms() {
        super();
    }

    // COMMANDS

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Forms f = (Forms) e.getSource();
        Point p = e.getPoint();
        if (f.isContainedInForm(p)) {
            f.changeColor(Forms.BORDER_COLOR_DEFAULT);
            f.changeController();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        Forms f = (Forms) e.getSource();
            System.out.println(f.getCoordinate());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
