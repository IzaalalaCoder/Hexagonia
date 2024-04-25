package hex.platform.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import hex.platform.view.DisplayWindow;
import hex.platform.view.popup.WarningPopUp;

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
        if (parent.getModel() != null) {
            if (WarningPopUp.preventSaveAble() == JOptionPane.YES_OPTION) {
                parent.dispose();
            }
        } 
    }
}
