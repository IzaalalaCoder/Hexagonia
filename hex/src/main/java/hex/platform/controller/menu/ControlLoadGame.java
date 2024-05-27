package hex.platform.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import hex.model.xml.reader.ReadingXML;
import hex.platform.view.DisplayWindow;
import hex.platform.view.popup.ErrorPopUp;
import hex.platform.view.popup.WarningPopUp;

public class ControlLoadGame implements ActionListener {

    // ATTRIBUTES 
    
    private final DisplayWindow parent;

    // CONSTRUCTORS

    public ControlLoadGame(DisplayWindow parent) {
        this.parent = parent;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parent.getModel() != null) {
            if (WarningPopUp.preventSaveAble() != JOptionPane.YES_OPTION) {
                return;
            }
        } 

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Sélectionner une sauvegarde");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
        fileChooser.addChoosableFileFilter(filter);

        int response = fileChooser.showOpenDialog(parent);
        File f = fileChooser.getSelectedFile();
        if (response == JFileChooser.APPROVE_OPTION && f != null) {
            try {
                lauchGame(f);
            } catch (IOException e1) {
               ErrorPopUp.preventOpenOrParseFileError();
            }
        }
    }
    
    // UTILS

    private void lauchGame(File file) throws IOException {
        ReadingXML readerXML = new ReadingXML(file);
        readerXML.readFileXML();
        this.parent.setModel(readerXML.getGameInFile());
    }
}
