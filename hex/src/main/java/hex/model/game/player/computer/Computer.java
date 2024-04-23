package hex.model.game.player.computer;

import hex.model.board.cell.Cell;
import hex.model.game.Action;
import hex.model.game.Game;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.PlayerType;

public class Computer extends AbstractPlayer {

    // ATTRIBUTES

    private Play play;
    private final Level level;
    private Game game;

    // CONSTRUCTORS 

    public Computer(int position, Level level) {
        this.play = null;
        this.level = level;
        this.type = PlayerType.COMPUTER;
        this.position = position;
        this.game = null;
    }

    // REQUESTS

    public Level getLevel() {
        return this.level;
    }

    // COMMANDS

    public void startPlay(Game game) {
        this.game = game;
        this.play = new Play(level, game.getBoard(), this);
    }

    public void play() {
        this.play.play();

       Action act = this.play.getAction();

        if (act != null) { 
            Cell c = act.getCell();
            this.game.takeCell(c.getAbscissa(), c.getOrdinate());
        } 
        
    }
}
