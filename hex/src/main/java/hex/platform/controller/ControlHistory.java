package hex.platform.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ControlHistory implements ActionListener{
    private final String PATH_HISTORY = "hex/src/main/resources/doc/HISTORY.pdf";

    @Override
    public void actionPerformed(ActionEvent e) {
        File output = new File(PATH_HISTORY); 
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(output);
        } catch (IOException e1) {
        }
    }

    

}
