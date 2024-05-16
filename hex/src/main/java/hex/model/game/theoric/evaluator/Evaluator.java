package hex.model.game.theoric.evaluator;

import hex.model.board.Board;
import hex.model.game.Game;

public class Evaluator {

    // STATIC

    public static Double evaluate(Board board) {

        if (board == null) {
            throw new NullPointerException("Null game");
        }

        Game game = new Game(board);
        boolean adversaryWin = game.existLine(0);
        boolean computerWin = game.existLine(1);

        if (adversaryWin) {
            return 800.0;
        } else if (computerWin) {
            return -800.0;
        }

        int numberPathAbleForAdversary = Evaluator.getNumberPath(board, 0);
        int numberPathAbleForComputer = Evaluator.getNumberPath(board, 1);

        return (double) numberPathAbleForComputer - numberPathAbleForAdversary;
    }

    public static int getNumberPath(Board b, int player) {
        return 0;
    }
}