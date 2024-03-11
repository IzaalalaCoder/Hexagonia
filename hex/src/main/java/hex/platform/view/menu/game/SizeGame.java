package hex.platform.view.menu.game;

public enum SizeGame {

    ELEVEN(11),

    NINE(9),

    EIGHT(8),

    SEVEN(7),

    SIX(6),

    FIVE(5),

    FOUR(4),
    THREE(3);

    private int size;

    SizeGame(int s) {
        this.size = s;
    }

    public Integer getSize() {
        return this.size;
    }
}
