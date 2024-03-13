package hex.platform.view.info;

import hex.platform.view.menu.MainMenu;

import javax.swing.*;

public enum PlayerMode {

    COMPUTER(new ImageIcon(MainMenu.PATH + "computer_dark.png")),
    PLAYER(new ImageIcon(MainMenu.PATH + "red.png"));

    private final ImageIcon img;

    PlayerMode(ImageIcon imageIcon) {
        this.img = imageIcon;
    }

    public ImageIcon getImg() {
        return this.img;
    }
}
