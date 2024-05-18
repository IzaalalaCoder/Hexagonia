package hex.model.game.theoric.structure.node;

import java.util.ArrayList;
import java.util.List;
import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.player.PlayerType;

public class Node {

    // ATTRIBUTES

    private final LabelPlayer labelPlayer;
    private final Board board;
    private final List<Node> successors;
    private Status status;
    private Node predecessor;
    private Double heuristicValue;

    // CONSTRUCTORS

    public Node(LabelPlayer labelPlayer, Board board) {
        this.labelPlayer = labelPlayer;
        this.status = Status.LEAF;
        this.successors = new ArrayList<>();
        this.predecessor = null;
        this.heuristicValue = 0.0;
        this.board = board;
    } 

    // REQUESTS

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Cell[] cells : this.board.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    result.append(" _ ");
                } else {
                    result.append(c.getPlayer().getType() == PlayerType.COMPUTER ? " X " : " O ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    public Board getActualBoard() {
        return this.board;
    }

    public LabelPlayer getLabelPlayer() {
        return this.labelPlayer;
    }
    
    public Status getStatus() {
        return this.status;
    }
    
    public List<Node> getSuccessor() {
        return this.successors;
    }
      
    public Node getPredecessor() {
        return this.predecessor;
    }

    public Double getHeuristicValue() {
        return this.heuristicValue;
    }

    // COMMANDS
    
    public void addSuccessor(Node successor) {
        this.successors.add(successor);
        this.status = Status.NODE;
    }

    @SuppressWarnings("unused")
    public void removeSuccessor(Node successor) {
        this.successors.remove(successor);
        if (this.successors.isEmpty()) {
            this.status = Status.LEAF;
        }
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public void setHeuristicValue(Double value) {
        this.heuristicValue = value;
        this.status = Status.TERMINAL;
    }
}