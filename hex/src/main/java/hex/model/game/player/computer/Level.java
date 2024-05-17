package hex.model.game.player.computer;

public enum Level {

    // VALUES 

    EASY(1),
    MEDIUM(2),
    HARD(3); // 12 a 9 thread ?
    //EXTREME(10, 4);

    // CONSTRUCTOR

    private Level(int sizeTail) {
        this.sizeTail = sizeTail;
    }

    // ATTRIBUTES 

    private final int sizeTail;

    // REQUESTS

    public int getSizeTail() {
        return sizeTail;
    }
}
