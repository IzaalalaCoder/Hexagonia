package hex.model.game.theoric.algo.strat;

import hex.model.game.Action;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.AbstractTheory;
import hex.model.game.theoric.Theory;
import hex.model.game.theoric.structure.Tree;

import java.io.File;
import java.util.List;

public class WinStrategy extends AbstractTheory {

    // ATTRIBUTES

    // CONSTRUCTORS

    public WinStrategy() {

    }

    // REQUESTS

    @Override
    public Tree getTree() {
        return null;
    }

    @Override
    public String getExplain() {
        return null;
    }

    @Override
    public File getBasicAnalysis() {
        return null;
    }

    @Override
    public List<Action> getEndAnalysis() {
        return null;
    }

    // COMMANDS

    @Override
    public void analysisByLevel(Level level) {

    }

    @Override
    public void analysisForPlayer() {

    }

    // UTILS
}
