package hex.game;

import hex.board.*;
import hex.board.cell.*;
import hex.game.complementary.Help;
import hex.game.player.Player;
import hex.game.player.PlayerName;
import hex.game.player.PlayerType;
import hex.util.structure.stack.Stack;

public class Game implements AbstractGame {

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
        this.mode = mode;
        board = createBoard(shape);
        players = initializePlayers(computerPlay);
    }

    public Game(int size) {
        this.mode = Mode.SAME;
        this.size = size;
        players = initializePlayers(false);
        board = createBoard(Shape.HEXAGONAL);
    }

    // REQUETES

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Mode getMode() {
        return mode;
    }

    @Override
    public Player[] getPlayers() {
        return players;
    }

    @Override
    public boolean isEndOfGame() {
        return false;
    }

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    @Override
    public Help getHelp() {
        return null;
    }

    @Override
    public Stack<Action> getHistory() {
        return null;
    }

    @Override
    public void consumeTurn() {

    }

    @Override
    public void replay() {

    }

    @Override
    public void reset() {

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

    private void managePlayer() {

    }
}
