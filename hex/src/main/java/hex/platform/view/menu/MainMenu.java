package hex.platform.view.menu;

import hex.platform.view.shapes.forms.Main;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JMenuBar {

    public static final String PATH = "hex/src/main/resources/assets/";

    public static ImageIcon getIcon(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    // ATTRIBUTES

    // CONSTRUCTOR

    public MainMenu() {
        super();
        this.implementMenu();
    }

    // REQUESTS


    // COMMANDS

    private void implementMenu() {
        for (Menu m : Menu.values()) {
            JMenu  menu = new JMenu(m.name());
            for (MenuItem it : m.getItems()) {
                if (it.getMenu() != null) {
                    it.getMenu().setIcon(MainMenu.getIcon(it.getIcon()));
                    menu.add(it.getMenu());

                } else {
                    menu.addSeparator();
                }
            }
            this.add(menu);
        }
    }
}
