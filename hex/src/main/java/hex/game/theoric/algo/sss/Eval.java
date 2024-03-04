package hex.game.theoric.algo.sss;

public enum Eval {
    A("Alive", false),
    R("Resolve", true);

    private final String name;
    private final Boolean resolve;

    Eval(String name, Boolean resolve) {
        this.name = name;
        this.resolve = resolve;
    }

    public String getName() {
        return name;
    }

    public Boolean getResolve() {
        return resolve;
    }
}
