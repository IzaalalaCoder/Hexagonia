package hex.model.game.theoric.algorithm.sss;

import java.util.ArrayList;
import java.util.List;
import hex.model.game.Game;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.AbstractTheory;
import hex.model.game.theoric.algorithm.sss.element.Triplet;
import hex.model.game.theoric.algorithm.sss.element.Type;
import hex.model.game.theoric.structure.node.Node;
import hex.model.game.theoric.structure.node.LabelPlayer;
import hex.model.game.theoric.structure.node.Status;

public class ThreeSStar extends AbstractTheory {

    // ATTRIBUTES

    private final List<Triplet<Node, Type, Double>> G;

    // CONSTRUCTORS

    public ThreeSStar(Game game, Level level) {
        super(game, level);
        this.G = new ArrayList<>();
        this.analyze(tree.getRoot());
    }

    // UTILS

    private void analyze(Node root) {
        this.sssStar(root);
        this.chooseBoard(root);
    }

    private void insert(Triplet<Node, Type, Double> triplet) {
        for (Triplet<Node, Type, Double> t : this.G) {
            if (triplet.getThirdValue() > t.getThirdValue()) {
                this.G.add(this.G.indexOf(t), triplet);
                return;
            }
        }
        this.G.add(triplet);
    }

    private void removeTripletHaveNode(Node node) {
        Triplet<Node, Type, Double> triplet = this.getTripletHaveNode(node);
        if (triplet != null) {
            this.G.remove(triplet);
        }
    }

    private Triplet<Node, Type, Double> getTripletHaveNode(Node node) {
        for (Triplet<Node, Type, Double> triplet : this.G) {
            if (node.equals(triplet.getFirstValue())) {
                return triplet;
            }
        }
        return null;
    }

    private void removeAllSuccessorOfNode(Node parent) {
        for (Node n : parent.getSuccessor()) {
            this.removeTripletHaveNode(n);
        }
    }

    private Triplet<Node, Type, Double> extractFirst() {
        Triplet<Node, Type, Double> first = this.G.get(0);
        this.G.remove(0);
        return first;
    }

    private Node getNodeLeftSuccessorNotYetDisplayed(Node root) {
        for (Node n : root.getSuccessor()) {
            Triplet<Node, Type, Double> triplet = this.getTripletHaveNode(n);
            if (triplet == null) {
                return n;
            }
        }
        return null;
    }

    private void sssStar(Node root) {
        this.G.add(new Triplet<>(root, Type.ALIVE, 900.0));
        
        while (!this.G.isEmpty()) {
            Triplet<Node, Type, Double> extract = this.extractFirst();
            Node n = extract.getFirstValue();
            if (n.equals(root) && extract.getSecondValue() == Type.RESOLVED) {
                return;
            }
            if (extract.getSecondValue() == Type.ALIVE) {
                if (n.getStatus() == Status.TERMINAL) {
                    Double value = Math.min(n.getHeuristicValue(), extract.getThirdValue());
                    n.setHeuristicValue(value);
                    this.insert(new Triplet<>(n, Type.RESOLVED, value));
                } else if (n.getLabelPlayer() == LabelPlayer.MAX) {
                    for (Node successor : n.getSuccessor()) {
                        this.insert(new Triplet<>(successor, Type.ALIVE, extract.getThirdValue()));
                    }
                } else {
                    Node leftSuccessor = this.getNodeLeftSuccessorNotYetDisplayed(n);
                    if (leftSuccessor != null) {
                        this.insert(new Triplet<>(leftSuccessor, Type.ALIVE, extract.getThirdValue()));
                    }
                }
            } else {
                Node predecessor = n.getPredecessor();
                int index = predecessor.getSuccessor().indexOf(n);
                predecessor.setHeuristicValue(extract.getThirdValue());
                if (n.getLabelPlayer() == LabelPlayer.MIN) {
                    this.insert(new Triplet<>(predecessor, Type.RESOLVED, extract.getThirdValue()));
                    this.removeAllSuccessorOfNode(predecessor);
                } else {
                    if (predecessor.getSuccessor().size() - 1 == index) {
                        this.insert(new Triplet<>(predecessor, Type.RESOLVED, extract.getThirdValue()));
                    } else {
                        this.insert(new Triplet<>(predecessor.getSuccessor().get(index + 1),
                                Type.ALIVE, extract.getThirdValue()));
                    }
                }
            }
        }
    }
}