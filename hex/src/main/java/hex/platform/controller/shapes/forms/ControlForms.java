package hex.platform.controller.shapes.forms;

import hex.game.Game;
import hex.platform.view.info.PlayerName;
import hex.platform.view.shapes.Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlForms extends MouseAdapter {

    // CONSTRUCTOR

    private JButton btn;
    private Game model;

    public ControlForms(JButton btn, Game model) {
        super();
        this.btn = btn;
        this.model = model;
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
            f.removeController();
            if (model != null) {
                PlayerName playerName = PlayerName.values()[model.getPositionCurrentPlayer()];
                f.changeColor(playerName.getDefaultColorForPlayer());
                model.consumeTurn();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Forms f = (Forms) e.getSource();
        Point p = e.getPoint();

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Forms f = (Forms) e.getSource();
        Point p = e.getPoint();

    }

}
