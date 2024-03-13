package hex.platform.view.info;

import java.awt.*;

public enum SizeGame {

    TWELVE(
        12,
        new Dimension(850, 700)
    ),

    ELEVEN(
        11,
        new Dimension(800, 650)
    ),

    TEN(
        10,
        new Dimension(700, 650)
    ),

    NINE(
        9,
        new Dimension(650, 600)
    ),

    EIGHT(
        8,
        new Dimension(600, 550)
    ),

    SEVEN(
        7,
        new Dimension(530, 500)
    ),

    SIX(
        6,
        new Dimension(450, 500)
    ),

    FIVE(
        5,
        new Dimension(400, 430)
    ),

    FOUR(
        4,
        new Dimension(320, 400)
    ),

    THREE(
        3,
        new Dimension(250, 350)
    );

    private int size;

    private Dimension dimension;

    SizeGame(int s, Dimension dimension) {
        this.size = s;
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Integer getSize() {
        return this.size;
    }
}
