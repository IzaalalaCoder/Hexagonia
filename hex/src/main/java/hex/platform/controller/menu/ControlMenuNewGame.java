package hex.platform.controller.menu;

import hex.model.game.Game;
import hex.platform.view.DisplayWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenuNewGame implements ActionListener {

    // ATTRIBUTES

    private DisplayWindow parent;
    private final boolean isComputer;
    private final boolean isOffline;
    private final int level;
    private final int size;

    // CONSTRUCTOR

    public ControlMenuNewGame(DisplayWindow parent, Boolean isComputer,
      Boolean isOffline, int level, int size) {
        this.parent = parent;
        this.isComputer = isComputer;
        this.isOffline = isOffline;
        this.level = level;
        this.size = size;
    }


    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.setModel(new Game(
                isOffline,
                isComputer,
                level,
                size));
    }
}
