package hex.platform.view.info;

import hex.platform.view.menu.MainMenu;

import javax.swing.*;

public enum PlayMode {

    OFFLINE(
        new ImageIcon(MainMenu.PATH + "computer_mode.png"),
        new PlayerMode[] { PlayerMode.COMPUTER, PlayerMode.PLAYER }),

    ONLINE(
        new ImageIcon(MainMenu.PATH + "reseau.png"),
        null
    );

    private final ImageIcon img;
    private final PlayerMode[] playerModes;

    public PlayerMode[] getTypePlayers() {
        return playerModes;
    }

    public ImageIcon getImg() {
        return this.img;
    }

    PlayMode(ImageIcon imageIcon, PlayerMode[] playerModes) {
        this.img = imageIcon;
        this.playerModes = playerModes;
    }
}
