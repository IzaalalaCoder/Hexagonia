package hex.game.player;

import hex.game.player.AbstractPlayer;
import hex.game.player.PlayerType;

public class Player extends AbstractPlayer {


    // Constructeurs

    public Player(PlayerType type, int position) {
        this.type = type;
        this.position = position;
    }
}
