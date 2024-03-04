package hex.game;

import hex.board.*;
import hex.board.cell.*;
import hex.game.player.Player;
import hex.game.player.PlayerName;
import hex.game.player.PlayerType;

public class Game {

    // CONSTANCES DE CLASSES

    public static final int MAX_NUMBER_OF_PLAYER = 2;

    // ATTRIBUTS

    private final Mode mode;
    private final Board board;
    private final Player[] players;

    // CONSTRUCTEURS

    public Game(Mode mode, boolean computerPlay, Shape shape) {
        board = createBoard(shape);
        this.mode = mode;
        players = initializePlayers(computerPlay);
    }

    // REQUETES

    public Board getBoard() {
        return board;
    }

    public Mode getMode() {
        return mode;
    }

    public Player[] getPlayers() {
        return players;
    }

    // COMMANDES



    // OUTILS

    private Board createBoard(Shape shape) {
        return new Board(12,12, shape);
    }

    private Player[] initializePlayers(boolean computerPlay) {
        Player[] players = new Player[Game.MAX_NUMBER_OF_PLAYER];
        players[0] = new Player(PlayerName.MAX, PlayerType.HUMAN);
        switch (this.mode) {
            case SAME ->
                players[1] = new Player(PlayerName.MIN, computerPlay
                        ? PlayerType.COMPUTER : PlayerType.HUMAN);
            case DISTANCE ->
                new Player(PlayerName.MIN, PlayerType.HUMAN);
        }
        return players;
    }
}
