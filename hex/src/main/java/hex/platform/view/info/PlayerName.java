package hex.platform.view.info;

import java.awt.*;

public enum PlayerName {

    // VALUES

    FIRST(Color.BLUE, 0),
    SECOND(Color.RED, 1);

    // ATTRIBUTES

    private final Color color;
    private final int position;

    // CONSTRUCTOR

    private PlayerName(Color c, int pos) {
        this.color = c;
        this.position = pos;
    }

    // REQUESTS

    public Color getDefaultColorForPlayer() {
        return this.color;
    }

    public int getPosition() {
        return position;
    }
}
