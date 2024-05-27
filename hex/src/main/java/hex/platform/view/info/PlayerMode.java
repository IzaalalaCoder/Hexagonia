package hex.platform.view.info;

import hex.platform.view.menu.MainMenu;
import javax.swing.*;

public enum PlayerMode {

    // VALUES

    COMPUTER(new ImageIcon(MainMenu.PATH + "computer_dark.png")),
    PLAYER(new ImageIcon(MainMenu.PATH + "red.png"));

    // ATTRIBUTES

    private final ImageIcon img;

    // CONSTRUCTORS

    PlayerMode(ImageIcon imageIcon) {
        this.img = imageIcon;
    }

    // REQUESTS

    public ImageIcon getImg() {
        return this.img;
    }
}
