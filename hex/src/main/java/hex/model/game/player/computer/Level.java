package hex.model.game.player.computer;

public enum Level {
    EASY(80, 1),
    MEDIUM(85, 2),
    HARD(90, 3); // 12 a 9 thread ?
    //EXTREME(10, 4);

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
