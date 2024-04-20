package hex.model.game.theoric.structure;

import java.util.ArrayList;
import java.util.List;

import hex.model.game.theoric.structure.util.LabelPlayer;
import hex.model.game.theoric.structure.util.Status;

public class Node {

    // ATTRIBUTES

    private final LabelPlayer labelPlayer;
    private Status status;
    private List<Node> successors;
    private Node predecessor;
    private Double heuristicValue;

    // CONSTRUCTORS

    public Node(LabelPlayer labelPlayer) {
        this.labelPlayer = labelPlayer;
        this.status = Status.LEAF;
        this.successors = new ArrayList<Node>();
        this.predecessor = null;
        this.heuristicValue = 0.0;
    } 

    // REQUESTS

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
    
    public void seStatus(Status status) {
        this.status = status;
    }
    
    public void addSuccessor(Node successor) {
        this.successors.add(successor);
    }
    
    public void removeSuccessor(Node successor) {
        this.successors.remove(successor);
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public void setHeuristicValue(Double heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

}
