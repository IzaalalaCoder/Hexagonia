package hex.game.player;

import java.awt.*;

public enum PlayerName {

    // VALUES

    MAX(Color.BLUE), MIN(Color.RED);

    // ATTRIBUTES

    private final Color color;

    // CONSTRUCTOR

    private PlayerName(Color c) {
        this.color = c;
    }

    // REQUESTS

    public Color getDefaultColorForPlayer() {
        return this.color;
    }
}
