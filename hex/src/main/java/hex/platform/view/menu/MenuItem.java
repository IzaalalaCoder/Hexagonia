package hex.platform.view.menu;

import javax.swing.*;

public enum MenuItem {

    // Game

    NEW_GAME(new JMenuItem("Nouvelle partie"), new ImageIcon(MainMenu.PATH + "new.png")),
    RULE_GAME(new JMenuItem("Règle du jeu"), new ImageIcon(MainMenu.PATH + "new.png")),
    SAVE_GAME(new JMenuItem("Sauvegarder la partie"), new ImageIcon(MainMenu.PATH + "new.png")),
    LOAD_GAME(new JMenuItem("Charger une partie"), new ImageIcon(MainMenu.PATH + "new.png")),

    // Historic

    REDO_HIST(new JMenuItem("Annuler"), new ImageIcon(MainMenu.PATH + "new.png")),
    UNDO_HIST(new JMenuItem("Refaire"), new ImageIcon(MainMenu.PATH + "new.png")),

    // Other

    ABOUT_OTH(new JMenuItem("A propos"), new ImageIcon(MainMenu.PATH + "new.png")),
    LOG_OTH(new JMenuItem("Afficher les logs"), new ImageIcon(MainMenu.PATH + "new.png")),

    //
    SEPARATOR(null, null);

    private final JMenuItem menu;
    public JMenuItem getMenu() {
        return this.menu;
    }

    private MenuItem(JMenuItem item, ImageIcon imageIcon) {
        this.menu = item;
        if (this.menu != null) {
            this.menu.setIcon(imageIcon);
        }
    }

}
