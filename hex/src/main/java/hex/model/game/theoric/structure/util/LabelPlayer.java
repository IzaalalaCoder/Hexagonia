package hex.model.game.theoric.structure.util;

public enum LabelPlayer {
    MAX(1),
    MIN(-1);

    private int result;

    private LabelPlayer(int r) {
        this.result = r;
    }

    public int getResult() {
        return this.result;
    }
}
