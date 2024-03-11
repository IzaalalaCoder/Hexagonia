package hex.platform.view.menu.game;

import hex.platform.view.menu.MainMenu;

import javax.swing.*;

public enum ModePlayer {

    COMPUTER(new ImageIcon(MainMenu.PATH + "computer_mode.png")),
    PLAYER(new ImageIcon(MainMenu.PATH + "red.png"));

    private final ImageIcon img;

    ModePlayer(ImageIcon imageIcon) {
        this.img = imageIcon;
    }

    public ImageIcon getImg() {
        return this.img;
    }
}
