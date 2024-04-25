package hex.platform.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import hex.model.board.cell.Cell;
import hex.model.game.AbstractGame;
import hex.model.game.Game;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.PlayerType;
import hex.model.game.player.computer.Computer;
import hex.platform.view.DisplayBoard;
import hex.platform.view.info.PlayerName;
import hex.platform.view.popup.EndGamePopUp;
import hex.platform.view.shapes.Forms;

public class ControlBoard {

    // ATTRIBUTES

    private final Game model;
    private final DisplayBoard frame;
    private final Forms[][] buttons;

    // CONSTRUCTORS

    public ControlBoard(Forms[][] buttons, Game model, DisplayBoard display) {
        this.model = model;
        this.buttons = buttons;
        this.frame = display;
        this.createController();
    }

    // COMMANDS
    private void createController() {
        this.model.addPropertyChangeListener(AbstractGame.PROP_TAKE_CELL_BY_COMPUTER, evt -> {
            Cell c = (Cell) evt.getNewValue();
            PlayerName playerName = PlayerName.values()[model.getPositionCurrentPlayer()];
            buttons[c.getAbscissa()][c.getOrdinate()].changeColor(playerName.getDefaultColorForPlayer());
            buttons[c.getAbscissa()][c.getOrdinate()].removeController();

            model.consumeTurn();
        });

        this.model.addPropertyChangeListener(AbstractGame.PROP_END_GAME, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                frame.removeAllController();
                String message = "";
                if (model.getIsGameWithComputer()) {
                    boolean isLost = model.getWinner().getType() == PlayerType.COMPUTER;
                    message += isLost ? "Dommage ! L'ordinateur a gagné" : 
                        "Félicitation ! Vous avez gagné";
                    EndGamePopUp.preventEndGame(message, isLost);
                } else {
                }
            }
            
        });

        this.model.addPropertyChangeListener(AbstractGame.PROP_CURR_PLAYER_ID, evt -> {
            if (model.isEndOfGame()) {
                return;
            }
            AbstractPlayer player = model.getCurrentPlayer();
            if (player.getType() == PlayerType.COMPUTER) {
                Computer computer = (Computer) model.getCurrentPlayer();
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) { }
                computer.play();
            }
        });
    }
}
