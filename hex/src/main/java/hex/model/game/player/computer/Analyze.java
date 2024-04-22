package hex.model.game.player.computer;

import hex.model.game.theoric.algorithm.Theory;

public enum Analyze {

    // VALUES
    
    MINIMAX(null),
    NEGAMAX(null),
    ALPHA_BETA(null),
    NEG_ALPHA_BETA(null),
    SSS_STAR(null);

    // ATTRIBUTES

    private Theory theory;

    // CONSTRUCTORS

    Analyze(Theory theory) {
        this.theory = theory;
    }

    // REQUESTS

    public Theory getTheory() {
        return theory;
    }
}