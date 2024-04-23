package hex.platform.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import hex.model.game.Game;
import hex.model.util.xml.WritingXML;
import hex.platform.view.DisplayWindow;

@SuppressWarnings("unused")
public class ControlSaveGame implements ActionListener {
    
    // CONSTANTS

    private final String PATH_HISTORY = "hex/src/main/resources/informations/newSave.xml";
    
    // ATTRIBUTES 
    
    private Game game;
    private DisplayWindow parent;
    
    // CONSTRUCTORS

    public ControlSaveGame(DisplayWindow parent) {
        this.game = null;
        this.parent = parent;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            WritingXML writer = new WritingXML(parent.getModel());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
}
