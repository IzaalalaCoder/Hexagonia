package hex.model.game.player.computer;

public enum Level {
    EASY(100),
    MEDIUM(50),
    HARD(30),
    EXTREME(20);

    private Level(int percent) {
        this.percent = percent;
    }

    private int percent;

    public int getPercent() {
        return percent;
    }
}
