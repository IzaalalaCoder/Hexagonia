package hex.model.game.theoric.structure;

import java.util.List;
import java.io.ObjectInputFilter.Status;

import hex.model.game.theoric.structure.util.LabelPlayer;

public interface Node {

    // REQUESTS

    public LabelPlayer getLabelPlayer();
    
    public Status geStatus();
    
    public List<Node> getSuccessor();
    
    public Node getPredecessor();

    public int getHeuristicValue();

    // COMMANDS
    
    public void seStatus(Status status);
    
    public void addSuccessor(Node successor);
    
    public void removeSuccessor(Node successor);

    public void setPredecessor(Node predecessor);

    public void setHeuristicValue(int heuristicValue);

}
