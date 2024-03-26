package hex.model.game.player;

public abstract class AbstractPlayer {

    // ATTRIBUTES
    protected PlayerType type;
    protected int position;

    // REQUESTS

    public PlayerType getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

}
