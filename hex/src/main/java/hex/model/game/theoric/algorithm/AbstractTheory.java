package hex.model.game.theoric.algorithm;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.tree.Tree;

public abstract class AbstractTheory implements Theory {

    // ATTRIBUTES

    protected Board startBoard;
    protected Board chooseBoard;
    protected Tree tree;

    // CONSTRUCTORS

    public AbstractTheory(Game game, Level level) {
        chooseBoard = null;
        tree = new Tree(game, level);
        tree.createTree();
        startBoard = game.getBoard();
    }

    // REQUESTS

    @Override
    public Cell getCell() {
        return this.getChooseCell();
    }

    // COMMANDS

    protected void chooseBoard(Node root) {
        Node nodeWithMinHeuristicValue = root.getSuccessor().get(0);
        for (Node node : root.getSuccessor()) {
            System.out.print(node.getHeuristicValue() + " ");

            nodeWithMinHeuristicValue = node.getHeuristicValue() < nodeWithMinHeuristicValue.getHeuristicValue()
                ? node : nodeWithMinHeuristicValue;
        }

        System.out.println("");
        System.out.println(nodeWithMinHeuristicValue.getHeuristicValue());
        chooseBoard = nodeWithMinHeuristicValue.getActualBoard();
    }

    // UTILS

    private Cell getChooseCell() {
        Cell choose = null;
        for (Cell[] cells : this.chooseBoard.getGrid()) {
            for (Cell c : cells) {
                int i = c.getAbscissa();
                int j = c.getOrdinate();
                if (this.startBoard.getGrid()[i][j].getState() == State.EMPTY
                    && c.getState() == State.PLAYER) {
                        choose = c;
                        break;
                }
            }
            if (choose != null) {
                break;
            }
        }
        return choose;
    }
}