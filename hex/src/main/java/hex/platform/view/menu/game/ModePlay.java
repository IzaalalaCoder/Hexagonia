package hex.platform.view.menu.game;

import hex.platform.view.menu.MainMenu;

import javax.swing.*;

public enum ModePlay {

    OFFLINE(
        new ImageIcon(MainMenu.PATH + "computer_mode.png"),
        new TypePlayer[] { TypePlayer.COMPUTER, TypePlayer.PLAYER }),

    ONLINE(
        new ImageIcon(MainMenu.PATH + "reseau.png"),
        null
    );

    private final ImageIcon img;
    private final TypePlayer[] typePlayers;

    public TypePlayer[] getTypePlayers() {
        return typePlayers;
    }

    public ImageIcon getImg() {
        return this.img;
    }

    ModePlay(ImageIcon imageIcon, TypePlayer[] typePlayers) {
        this.img = imageIcon;
        this.typePlayers = typePlayers;
    }
}
