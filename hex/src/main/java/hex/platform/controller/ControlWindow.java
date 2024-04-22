package hex.platform.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hex.platform.view.DisplayWindow;

public class ControlWindow implements ActionListener {

    // ATTRIBUTES

    private DisplayWindow parent;

    // CONSTRUCTOR 

    public ControlWindow(DisplayWindow parent) {
        this.parent = parent;
    }

    // COMMANDS
    
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.dispose();
    }
}
