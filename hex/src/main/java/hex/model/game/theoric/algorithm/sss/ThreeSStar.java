package hex.model.game.theoric.algorithm.sss;

import java.util.ArrayList;
import java.util.List;
import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.algorithm.Theory;
import hex.model.game.theoric.algorithm.tree.Arborescence;
import hex.model.game.theoric.structure.Node;
import hex.model.game.theoric.structure.util.LabelPlayer;
import hex.model.game.theoric.structure.util.Status;

public class ThreeSStar implements Theory {

    // ATTRIBUTES

    private final List<Triplet<Node, Type, Double>> G;

    // CONSTRUCTORS

    public ThreeSStar(Board board, Level level) {
        this.G = new ArrayList<>();
        Arborescence a = new Arborescence(board);
        Node root = a.createArborescence(level);
        this.analyze(root);
    }

    // REQUESTS

    @Override
    public Cell getChooseCell() {
        return null;
    }

    // COMMANDS

    @Override
    public void analyze(Node root) {
        this.sssStar(root);
    }
   
    // UTILS

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
        for (Triplet<Node, Type, Double> triplet : this.G) {
            if (node.equals(triplet.getFirstValue())) {
                this.G.remove(triplet);
                return;
            }
        }
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

    private Double sssStar(Node root) {
        this.G.add(new Triplet<Node,Type,Double>(root, Type.ALIVE, Double.MAX_VALUE));
        
        Double val = Double.MAX_VALUE;
        
        while (true) {
            Triplet<Node, Type, Double> extract = this.extractFirst();
            Node n = extract.getFirstValue();
            if (n.equals(root) && extract.getSecondValue() == Type.RESOLVED) {
                val = n.getHeuristicValue();
                break;
            }
            
            if (extract.getSecondValue() == Type.ALIVE) {
                if (extract.getFirstValue().getStatus() == Status.TERMINAL) {
                    this.insert(new Triplet<>(n, Type.RESOLVED, Math.min(n.getHeuristicValue(), extract.getThirdValue())));
                } else if (n.getLabelPlayer() == LabelPlayer.MAX) {
                    for (Node successor : n.getSuccessor()) {
                        this.insert(new Triplet<Node,Type,Double>(successor, Type.ALIVE, extract.getThirdValue()));
                    }
                } else {
                    this.insert(new Triplet<Node,Type,Double>(n.getSuccessor().get(0), Type.ALIVE, extract.getThirdValue()));
                }
            } else {
                Node predecessor = n.getPredecessor();
                int index = predecessor.getSuccessor().indexOf(n);
                if (n.getLabelPlayer() == LabelPlayer.MIN) {
                    this.insert(new Triplet<>(predecessor, Type.RESOLVED, extract.getThirdValue()));
                    this.removeAllSuccessorOfNode(predecessor);
                } else {
                    if (predecessor.getSuccessor().size() - 1 == index) {
                        this.insert(new Triplet<Node,Type,Double>(predecessor, Type.RESOLVED, extract.getThirdValue()));
                    } else {
                        this.insert(new Triplet<Node,Type,Double>(predecessor.getSuccessor().get(index + 1), Type.ALIVE, extract.getThirdValue()));
                    }
                }
            }
        } 

        return val;
    }

}
