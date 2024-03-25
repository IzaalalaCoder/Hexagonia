package hex.game.player.computer;

import hex.game.player.AbstractPlayer;
import hex.game.player.PlayerType;

public class Computer extends AbstractPlayer {

    public Computer(int position) {
        this.type = PlayerType.COMPUTER;
        this.position = position;
    }
}
