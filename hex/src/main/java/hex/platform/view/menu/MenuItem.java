package hex.platform.view.menu;

import javax.swing.*;
import java.awt.*;

public enum MenuItem {

    // Game

    NEW_GAME(
            new MenuNewGame(),
            new ImageIcon(MainMenu.PATH + "new.png")),

    JOIN_GAME (
            new JMenuItem("Joindre une partie"),
            new ImageIcon(MainMenu.PATH + "new.png")
    ),
    RULE_GAME(
            new JMenuItem("Règle du jeu"),
            new ImageIcon(MainMenu.PATH + "rule.png")),
    SAVE_GAME(
            new JMenuItem("Sauvegarder la partie"),
            new ImageIcon(MainMenu.PATH + "save.png")),
    LOAD_GAME(
            new JMenuItem("Charger une partie"),
            new ImageIcon(MainMenu.PATH + "load.png")),

    // Historic

    REDO_HIST(
            new JMenuItem("Annuler"),
            new ImageIcon(MainMenu.PATH + "redo.png")),
    UNDO_HIST(
            new JMenuItem("Refaire"),
            new ImageIcon(MainMenu.PATH + "undo.png")),

    // Other

    ABOUT_OTH(
            new JMenuItem("A propos"),
            new ImageIcon(MainMenu.PATH + "about.png")),
    LOG_OTH(
            new JMenuItem("Afficher les logs"),
            new ImageIcon(MainMenu.PATH + "log.png")),

    //
    SEPARATOR(null, null),

    QUIT(
        new JMenuItem("QUIT"),
        new ImageIcon(MainMenu.PATH + "quit.png")
    );

    private final JMenuItem menu;

    private final ImageIcon icon;

    public ImageIcon getIcon() {
        return this.icon;
    }

    public JMenuItem getMenu() {
        return this.menu;
    }

    MenuItem(JMenuItem item, ImageIcon icon) {
        this.menu = item;
        this.icon = icon;
    }

}
