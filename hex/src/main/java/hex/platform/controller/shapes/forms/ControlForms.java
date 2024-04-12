package hex.platform.controller.shapes.forms;

import hex.model.game.AbstractGame;
import hex.model.game.Game;
import hex.platform.view.info.PlayerName;
import hex.platform.view.shapes.Forms;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControlForms extends MouseAdapter {

    // CONSTRUCTOR

    private final Game model;

    public ControlForms(Game model) {
        super();
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
            if (model != null) {
                PlayerName playerName = PlayerName.values()[model.getPositionCurrentPlayer()];
                f.changeColor(playerName.getDefaultColorForPlayer());
                model.takeCell(f.getAbscissa(), f.getOrdinate());
                model.consumeTurn();
            }
            f.removeController();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Forms f = (Forms) e.getSource();
        PlayerName playerName = PlayerName.values()[model.getPositionCurrentPlayer()];
        int r = playerName.getDefaultColorForPlayer().getRed();
        int g = playerName.getDefaultColorForPlayer().getGreen();
        int b = playerName.getDefaultColorForPlayer().getBlue();
        f.changeColor(new Color(r, g, b, 50));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Forms f = (Forms) e.getSource();
        f.changeColor(Color.WHITE);
    }

}
