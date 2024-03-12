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
    private final int size;

    // CONSTRUCTEURS

    public Game(Mode mode, boolean computerPlay, Shape shape) {
        this.size = 11;
        board = createBoard(shape);
        this.mode = mode;
        players = initializePlayers(computerPlay);
    }

    public Game() {
        board = createBoard(Shape.HEXAGONAL);
        this.mode = Mode.SAME;
        players = initializePlayers(false);
        this.size = 11;
    }

    // REQUETES


    public int getSize() {
        return size;
    }

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
        return new Board(this.size,this.size, shape);
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
