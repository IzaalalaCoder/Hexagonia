package hex.platform.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ControlOnFileGame implements ActionListener {

    private String path;
    
    public ControlOnFileGame(String path) {
        this.path = path;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        File output = new File(path); 
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(output);
        } catch (IOException e1) {
        }
    }
    
}
