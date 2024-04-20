package hex.model.game.player.computer;

public enum Level {
    EASY(10, 2),
    MEDIUM(7, 3),
    HARD(6, 4), 
    EXTREME(5, 5);

    private Level(int percent, int sizeTail) {
        this.percent = percent;
        this.sizeTail = sizeTail;
    }

    private int percent;
    private int sizeTail;

    public int getPercent() {
        return percent;
    }

    public int getSizeTail() {
        return sizeTail;
    }
}
