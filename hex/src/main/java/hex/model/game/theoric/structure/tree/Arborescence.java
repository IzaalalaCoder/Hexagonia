package hex.model.game.theoric.structure.tree;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import hex.model.game.Game;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.computer.Computer;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.util.LabelPlayer;
import hex.model.game.theoric.structure.node.util.Status;

public class Arborescence {

    // ATTRIBUTES

    private Node root;
    private int currentPlayer;
    private Game game;

    // CONSTRUCTORS
    
    public Arborescence(Game game) {
        this.game = game;
        this.root = new Node(LabelPlayer.MAX, game.getBoard());
        this.currentPlayer = 1;
    }

    // REQUESTS

    public Node createArborescence(Level level) {
        int sizeTail = level.getSizeTail();
        this.buildTree(root, sizeTail);
        this.calculateMinimaxValue(this.root);
        return root;
    }

    // UTILS

    private void buildTree(Node node, int tail) {
        if (tail == 0) {
            return;
        }
        Board board = node.getActualBoard().getCopy();
        AbstractPlayer p = board.getAbstractPlayers()[currentPlayer];
        for (Cell[] cells : board.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    c.setPlayer(p);
                    Node newNode = new Node(this.currentPlayer == 1 ? LabelPlayer.MIN : LabelPlayer.MAX, board.getCopy());
                    this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
                    this.buildTree(newNode, tail - 1);
                    this.currentPlayer = this.currentPlayer == 0 ? 1 : 0;
                    newNode.setPredecessor(node);
                    node.addSuccessor(newNode);
                    c.unsetPlayer();
                }
            }
        }
    }

    private void calculateMinimaxValue(Node node) {
        if (node.getStatus() == Status.LEAF) {
            Computer computer = (Computer) this.game.getComputer();
            Game newGame = new Game(true, computer.getLevel().ordinal(), this.game.getBoard().getGrid().length);
            newGame.setBoard(node.getActualBoard());
            if (newGame.existLine(currentPlayer == 0 ? 1 : 0)) {
                node.setHeuristicValue(Double.MIN_VALUE);
            } else {
                node.setHeuristicValue(this.numberOfTokensConnected(newGame));
            }
        }

        for (Node n : node.getSuccessor()) {
            this.calculateMinimaxValue(n);
        }
    }

    private Double numberOfTokensConnected(Game g) {
        Double count = 0.0;

        for (Cell[] cells : g.getBoard().getGrid()) {
            for (Cell c : cells) {
                count += this.browseFromCell(c);
            }
        }

        return count;
    }

    private Double browseFromCell(Cell c) {
        Double count = 0.0;
        for (Direction d : c.getDirections().keySet()) {
            Cell newCell = c.getCellOnDir(d);
            if (newCell != null && !newCell.getVisited() && newCell.getState() != State.EMPTY
                && newCell.getPlayer().getPosition() == this.currentPlayer) {
                    newCell.setVisit(true);
                    
                    count += this.browseFromCell(newCell) + 1;
            }   
        }
        
        return count;
    }
}
