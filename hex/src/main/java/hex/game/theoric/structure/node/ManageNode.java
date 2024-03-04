package hex.game.theoric.structure.node;

import hex.game.player.Player;

import java.util.List;

public interface ManageNode<E> {

    // REQUESTS

    Boolean getIsFinish();

    Player getLabel();

    E getConfiguration();

    Status getStatus();

    Double getNodeHeuristicValue();

    int getConnectionFactor();

    List<ManageNode<E>> getSuccessors();

    ManageNode<E> getPredecessor();

    // COMMANDS

    void addSuccessors(ManageNode<E> node);

    void removeSuccessors(ManageNode<E> node);

    void removeAllSuccessors();

    void setStatus(Status s);

    void modifyFinish();

}
