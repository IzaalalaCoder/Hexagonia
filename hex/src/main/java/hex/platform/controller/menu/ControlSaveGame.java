package hex.platform.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import hex.model.game.Game;
import hex.model.util.xml.WritingXML;
import hex.platform.view.DisplayWindow;
import hex.platform.view.popup.ErrorPopUp;

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
        if (parent.getModel() == null) {
            ErrorPopUp.preventError();
            return;
        }

        WritingXML writer = new WritingXML(parent.getModel());
        try {
            writer.writeXMLFile();
        } catch (ParserConfigurationException e1) {} 
        catch (TransformerException e1) {}

        File f = writer.getGeneratedFile();

        JFileChooser chooserDirectory = new JFileChooser();
        chooserDirectory.setCurrentDirectory(new java.io.File("."));
        chooserDirectory.setDialogTitle("Choisir l'emplacement");
        chooserDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserDirectory.setAcceptAllFileFilterUsed(false);

        int result = chooserDirectory.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) { 
            try {
                Files.move(f.toPath(), Paths.get(chooserDirectory.getSelectedFile() + "/" + f.getName()));
            } catch (IOException e1) {}
        }
    }
    
}
