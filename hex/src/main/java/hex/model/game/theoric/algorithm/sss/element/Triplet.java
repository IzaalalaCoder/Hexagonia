package hex.model.game.theoric.algorithm.sss.element;

public class Triplet<A, B, C> {

    // ATTRIBUTES

    private final A firstValue;
    private final B secondValue;
    private final C thirdValue;

    // CONSTRUCTOR

    public Triplet(A a, B b, C c) {
        this.firstValue = a;
        this.secondValue = b;
        this.thirdValue = c;
    }

    // REQUESTS

    public A getFirstValue() {
        return this.firstValue;
    }

    public B getSecondValue() {
        return this.secondValue;
    }

    public C getThirdValue() {
        return this.thirdValue;
    }
} 