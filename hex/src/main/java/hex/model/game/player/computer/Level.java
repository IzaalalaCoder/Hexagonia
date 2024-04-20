package hex.model.game.player.computer;

public enum Level {
    EASY(8),
    MEDIUM(6),
    HARD(4), 
    EXTREME(2);

    private Level(int percent) {
        this.percent = percent;
    }

    private int percent;

    public int getPercent() {
        return percent;
    }
}
