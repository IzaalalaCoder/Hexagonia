package hex.platform.controller.menu;

import hex.model.game.Game;
import hex.platform.view.DisplayWindow;
import hex.platform.view.popup.WarningPopUp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControlMenuNewGame implements ActionListener {

    // ATTRIBUTES

    private final DisplayWindow parent;
    private final boolean isComputer;
    private final int level;
    private final int size;

    // CONSTRUCTOR

    public ControlMenuNewGame(DisplayWindow parent, Boolean isComputer, int level, int size) {
        this.parent = parent;
        this.isComputer = isComputer;
        this.level = level;
        this.size = size;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parent.getModel() != null) {
            if (WarningPopUp.preventSaveAble() == JOptionPane.NO_OPTION) {
                return;
            }
        } 
        parent.setModel(new Game(
                isComputer,
                level,
                size));
    }
}
