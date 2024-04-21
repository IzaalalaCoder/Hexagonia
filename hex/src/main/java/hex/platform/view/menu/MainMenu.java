package hex.platform.view.menu;

import hex.platform.controller.ControlRules;
import hex.platform.view.DisplayWindow;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainMenu extends JMenuBar {

    public static final String PATH = "hex/src/main/resources/assets/";

    public static ImageIcon getIcon(ImageIcon imageIcon) {
        if (imageIcon == null) {
            return null;
        }
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    private Map<MenuItem, JMenuItem> subMenus;

    // CONSTRUCTOR

    public MainMenu(DisplayWindow parent) {
        super();
        this.subMenus = new HashMap<>();
        this.implementMenu();
        this.createController(parent);
    }

    // UTILS

    private void implementMenu() {
        for (Menu m : Menu.values()) {
            JMenu  menu = new JMenu(m.name());
            for (MenuItem it : m.getItems()) {
                if (it.getMenu() != null) {
                    it.getMenu().setIcon(MainMenu.getIcon(it.getIcon()));
                    menu.add(it.getMenu());
                    this.subMenus.put(it, it.getMenu());
                } else {
                    menu.addSeparator();
                }
            }
            this.add(menu);
        }
        this.add(MenuItem.QUIT.getMenu());
    }

    private void createController(DisplayWindow parent) {
        // Add controller on new game menu

        MenuNewGame newGameMenu = (MenuNewGame) this.subMenus.get(MenuItem.NEW_GAME);
        newGameMenu.createController(parent);


        JMenuItem itemRule = this.subMenus.get(MenuItem.RULE_GAME);
        itemRule.addActionListener(new ControlRules());

    }
}
