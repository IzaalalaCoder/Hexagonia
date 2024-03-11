package hex.platform.view.menu;

import hex.platform.view.shapes.forms.Main;

import javax.swing.*;

public class MainMenu extends JMenuBar {

    public static final String PATH = "hex/src/main/resources/assets/";

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
                    menu.add(it.getMenu());
                } else {
                    menu.addSeparator();
                }
            }
            this.add(menu);
        }
    }
}
