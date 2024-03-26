package hex.model.game.player.computer;

import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.PlayerType;

public class Computer extends AbstractPlayer {

    public Computer(int position) {
        this.type = PlayerType.COMPUTER;
        this.position = position;
    }
}
