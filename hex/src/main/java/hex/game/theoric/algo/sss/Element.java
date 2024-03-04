package hex.game.theoric.algo.sss;

public class Element implements Comparable<Element> {

    // CONSTANTS

    public static int INFINITY = Integer.MAX_VALUE;

    // ATTRIBUTES

    private final int identified;
    private Eval eval;
    private int valueMinimax;

    // CONSTRUCTORS

    public Element(int id, Eval e) {
        this.identified = id;
        this.eval = e;
        this.valueMinimax = Element.INFINITY;
    }

    // REQUESTS

    @Override
    public int compareTo(Element o) {
        Integer value = o.valueMinimax;
        return Integer.valueOf(valueMinimax).compareTo(value);
    }

    public int getIdentified() {
        return this.identified;
    }

    public Eval getEval() {
        return this.eval;
    }

    public int getValueMinimax() {
        return this.valueMinimax;
    }

    // COMMANDS

    public void setEval(Eval e) {
        if (e == null) {
            throw new IllegalArgumentException("");
        }
        this.eval = e;
    }

    public void setValueMinimax(int minimax) {
        if (minimax < 0) {
            throw new IllegalArgumentException("");
        }
        this.valueMinimax = minimax;
    }

}
