package hex.platform.controller;

import hex.model.board.cell.Cell;
import hex.model.game.AbstractGame;
import hex.model.game.Game;
import hex.platform.view.info.PlayerName;
import hex.platform.view.shapes.Forms;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControlBoard {

    private Game model;
    private Forms[][] allButtons;

    public ControlBoard(Forms[][] buttons, Game model) {
        this.model = model;
        this.allButtons = buttons;
    }

    private void createController() {

    }
}
