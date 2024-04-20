package hex.model.game.theoric.structure;

import java.util.List;
import java.io.ObjectInputFilter.Status;

import hex.model.game.theoric.structure.util.LabelPlayer;

public interface Node {

    // REQUESTS

    public LabelPlayer getLabelPlayer();
    
    public Status geStatus();
    
    public List<Tree> getSuccessor();
    
    public Node getPredecessor();

    public int getHeuristicValue();

    // COMMANDS
    
    public void seStatus(Status status);
    
    public void addSuccessor(Tree subTree);
    
    public void removeSuccessor(Tree subTree);

    public void setPredecessor(Node parent);

    public void setHeuristicValue(int heuristicValue);

}
