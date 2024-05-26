package hex.model.game.theoric.structure.tree;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Game;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.evaluator.Evaluator;
import hex.model.game.theoric.evaluator.Heuristic;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.LabelPlayer;

public class Tree {

    // ATTRIBUTES

    private final Node root;
    private final Level level;
    private int currentPlayer;

    // CONSTRUCTORS
    
    public Tree(Game game, Level level) {
        this.level = level;
        this.root = new Node(LabelPlayer.MIN, game.getBoard());
        this.currentPlayer = 1;
    }

    // COMMANDS

    public void createTree() {
        this.buildTree(root, 0);
    }

    // REQUESTS

    public Node getRoot() {
        return root;
    }

    // UTILS

    private void buildTree(Node node, int tail) {
        if (tail == level.getSizeTail()) {
            return;
        }
        Board board = node.getActualBoard();
        AbstractPlayer p = board.getAbstractPlayers()[currentPlayer];
        for (Cell[] cells : board.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    c.setPlayer(p);
                    Node newNode = new Node(this.currentPlayer == 1 ? LabelPlayer.MAX : LabelPlayer.MIN, board.getCopy());
                    if (tail == level.getSizeTail() - 1) {
                        System.out.println(board);
                        Heuristic h = new Heuristic();
                        newNode.setHeuristicValue(Evaluator.evaluate(board, 1));
                        System.out.println(newNode.getHeuristicValue());
                    }
                    this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
                    this.buildTree(newNode, tail + 1);
                    this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
                    newNode.setPredecessor(node);
                    node.addSuccessor(newNode);
                    c.unsetPlayer();
                }
            }
        }
    }
}