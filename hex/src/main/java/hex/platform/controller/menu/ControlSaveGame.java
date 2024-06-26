package hex.platform.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import hex.model.xml.writer.WritingXML;
import hex.platform.view.DisplayWindow;
import hex.platform.view.popup.ErrorPopUp;

public class ControlSaveGame implements ActionListener {

    // ATTRIBUTES

    private final DisplayWindow parent;
    
    // CONSTRUCTORS

    public ControlSaveGame(DisplayWindow parent) {
        this.parent = parent;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parent.getModel() == null) {
            ErrorPopUp.preventStartGameError();
            return;
        }

        WritingXML writer = new WritingXML(parent.getModel());
        try {
            writer.writeXMLFile();
        } catch (ParserConfigurationException | TransformerException ignored) {}

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
            } catch (IOException ignored) {}
        }
    }
}