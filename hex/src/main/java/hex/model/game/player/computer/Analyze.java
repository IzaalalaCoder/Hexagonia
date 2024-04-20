package hex.model.game.player.computer;

import hex.model.game.theoric.algorithm.Theory;

public enum Analyze {
    MINIMAX(null),
    NEGAMAX(null),
    ALPHA_BETA(null),
    NEG_ALPHA_BETA(null),
    SSS_STAR(null)
    ;

    private Theory theory;

    Analyze(Theory theory) {
        this.theory = theory;
    }

    public Theory getTheory() {
        return theory;
    }
}