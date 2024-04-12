package hex.model.game.player.computer;

import hex.model.board.cell.Cell;
import hex.model.game.Action;
import hex.model.game.Game;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.PlayerType;

import java.util.List;

public class Computer extends AbstractPlayer {

    private Play play;

    private Level level;

    private Game game;

    public Computer(int position, Level level) {
        this.play = null;
        this.level = level;
        this.type = PlayerType.COMPUTER;
        this.position = position;
        this.game = null;
    }

    public void consume() {
        this.game.consumeTurn();
    }
    public void setGame(Game game) {
        this.game = game;
        this.play = new Play(level, game.getBoard(), this);
    }

    public void play() {
        this.play.play();

        Action act = this.play.getAction();
        System.out.println("JAI CHOISI x " + act.getCell().getAbscissa() + "y " + act.getCell().getOrdinate());
        Cell c = act.getCell();

        this.game.takeCell(c.getAbscissa(), c.getOrdinate());
    }
}
