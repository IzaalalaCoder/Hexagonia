package hex.platform.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ControlRules implements ActionListener {
    private final String PATH_RULES = "hex/src/main/resources/doc/RULES.pdf";

    @Override
    public void actionPerformed(ActionEvent e) {
        File output = new File(PATH_RULES); 
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(output);
        } catch (IOException e1) {
        }
    }

    

}
