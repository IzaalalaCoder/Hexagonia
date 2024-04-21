package hex.model.game.theoric.structure;

import java.util.ArrayList;
import java.util.List;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.player.PlayerType;
import hex.model.game.theoric.structure.util.LabelPlayer;
import hex.model.game.theoric.structure.util.Status;

public class Node {

    // ATTRIBUTES

    private final LabelPlayer labelPlayer;
    private final Board board;
    private Status status;
    private List<Node> successors;
    private Node predecessor;
    private Double heuristicValue;

    // CONSTRUCTORS

    public Node(LabelPlayer labelPlayer, Board board) {
        this.labelPlayer = labelPlayer;
        this.status = Status.LEAF;
        this.successors = new ArrayList<Node>();
        this.predecessor = null;
        this.heuristicValue = 0.0;
        this.board = board;
    } 

    // REQUESTS

    @Override
    public String toString() {
        String result = "";

        for (Cell[] cells : this.board.getGrid()) {
            for (Cell c : cells) {
                if (c.getState() == State.EMPTY) {
                    result += " _ ";
                } else {
                    result += c.getPlayer().getType() 
                        == PlayerType.COMPUTER ? " X " : " O ";
                }
            }
            result += "\n";
        }

        return result;
    }

    public Board getActualBoard() {
        return this.board;
    }

    public LabelPlayer getLabelPlayer() {
        return this.labelPlayer;
    }
    
    public Status geStatus() {
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
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public void addSuccessor(Node successor) {
        this.successors.add(successor);
        this.status = Status.NODE;
    }
    
    public void removeSuccessor(Node successor) {
        this.successors.remove(successor);
        if (this.successors.size() == 0) {
            this.status = Status.LEAF;
        }
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public void setHeuristicValue(Double heuristicValue) {
        this.heuristicValue = heuristicValue;
        this.status = Status.TERMINAL;
    }

}
