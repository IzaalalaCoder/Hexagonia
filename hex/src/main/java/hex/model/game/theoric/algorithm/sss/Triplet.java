package hex.model.game.theoric.algorithm.sss;

public class Triplet<A, B, C> {

    // ATTRIBUTES

    private A firstValue;
    private B secondValue;
    private C thirdValue;

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

    // COMMANDS

    public void setFirstValue(A firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(B secondValue) {
        this.secondValue = secondValue;
    }

    public void setThirdValue(C thirdValue) {
        this.thirdValue = thirdValue;
    }
} 