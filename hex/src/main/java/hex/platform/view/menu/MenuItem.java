package hex.platform.view.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public enum MenuItem {

    // Game

    NEW_GAME(
            new MenuNewGame(),
            new ImageIcon(MainMenu.PATH + "new.png")),
    RULE_GAME(
            new JMenuItem("Règle du jeu"),
            new ImageIcon(MainMenu.PATH + "rule.png")),
    HISTORY_GAME(
        new JMenuItem("Histoire du jeu"),
        new ImageIcon(MainMenu.PATH + "history.png")),
    SAVE_GAME(
            new JMenuItem("Sauvegarder la partie"),
            new ImageIcon(MainMenu.PATH + "save.png")),
    LOAD_GAME(
            new JMenuItem("Charger une partie"),
            new ImageIcon(MainMenu.PATH + "load.png")),

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
