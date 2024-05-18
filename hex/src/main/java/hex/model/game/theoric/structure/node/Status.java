package hex.model.game.theoric.structure.node;

public enum Status {
    LEAF, // not children
    NODE, // have children
    TERMINAL; // heuristic value find
}
