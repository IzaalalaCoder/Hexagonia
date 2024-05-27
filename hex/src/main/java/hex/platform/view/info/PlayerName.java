package hex.platform.view.info;

import java.awt.*;

public enum PlayerName {

    // VALUES

    FIRST(Color.BLUE),
    SECOND(Color.RED);

    // ATTRIBUTES

    private final Color color;

    // CONSTRUCTOR

    PlayerName(Color c) {
        this.color = c;
    }

    // REQUESTS

    public Color getDefaultColorForPlayer() {
        return this.color;
    }

}
