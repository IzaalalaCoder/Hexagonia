package hex.platform.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hex.model.game.Game;

public class ControleSaveGame implements ActionListener {
    //private final String PATH_HISTORY = "hex/src/main/resources/informations/example_save.xml";
    // ATTRIBUTES 
    
    private Game game;
    
    // CONSTRUCTORS

    public ControleSaveGame(Game game) {
        this.game = game;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
