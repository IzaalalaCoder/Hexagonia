package hex.model.game.player.computer;

public enum Level {

    // VALUES 

    EASY(90, 1),
    MEDIUM(90, 2),
    HARD(90, 3); // 12 a 9 thread ?
    //EXTREME(10, 4);

    // CONSTRUCTOR

    private Level(int percent, int sizeTail) {
        this.percent = percent;
        this.sizeTail = sizeTail;
    }

    // ATTRIBUTES 

    private int percent;
    private int sizeTail;

    // REQUESTS
    
    public int getPercent() {
        return percent;
    }

    public int getSizeTail() {
        return sizeTail;
    }
}
