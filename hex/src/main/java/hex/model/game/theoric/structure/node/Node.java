package hex.model.game.theoric.structure.node;

import hex.model.game.player.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;

public class Node<E> implements ManageNode<E> {

    // ATTRIBUTES

    private final AbstractPlayer label;
    private final E config;
    private Status status;
    private final ManageNode<E> parent;
    private final List<ManageNode<E>> children;

    // CONSTRUCTORS

    public Node(Node<E> parent, AbstractPlayer label, E configuration) {
        this.label = label;
        this.config = configuration;
        this.parent = parent;
        this.status = Status.LEAF;
        this.children = new ArrayList<>();
    }

    // REQUESTS

    @Override
    public Boolean getIsFinish() {
        return this.status == Status.TERMINAL;
    }

    @Override
    public AbstractPlayer getLabel() {
        return this.label;
    }

    @Override
    public E getConfiguration() {
        return this.config;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public Double getNodeHeuristicValue() {
        if (this.getIsFinish()) {
            return 0.0;
        }
        return null;
    }

    @Override
    public int getConnectionFactor() {
        return 0;
    }

    @Override
    public List<ManageNode<E>> getSuccessors() {
        return this.children;
    }

    @Override
    public ManageNode<E> getPredecessor() {
        return this.parent;
    }

    // COMMANDS

    @Override
    public void addSuccessors(ManageNode<E> node) {
        if (node == null) {
            throw new IllegalArgumentException("");
        }
        if (this.children.contains(node)) {
            throw new IllegalArgumentException("");
        }

        this.children.add(node);
    }

    @Override
    public void removeSuccessors(ManageNode<E> node) {
        if (node == null) {
            throw new IllegalArgumentException("");
        }
        if (!this.children.contains(node)) {
            throw new IllegalArgumentException("");
        }

        if (!node.getSuccessors().isEmpty()) {
            for (ManageNode<E> n : node.getSuccessors()) {
                n.removeAllSuccessors();
            }
        }
        this.children.remove(node);
    }

    @Override
    public void removeAllSuccessors() {
        if (!this.getSuccessors().isEmpty()) {
            for (ManageNode<E> n : this.getSuccessors()) {
                n.removeAllSuccessors();
                this.children.remove(n);
            }
        }
    }

    @Override
    public void setStatus(Status s) {
        if (s == null) {
            throw new IllegalArgumentException("");
        }
        this.status = s;
    }

    @Override
    public void modifyFinish() {
        this.status = Status.TERMINAL;
    }
}
