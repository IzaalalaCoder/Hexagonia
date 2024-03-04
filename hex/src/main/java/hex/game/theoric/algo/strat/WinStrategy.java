package hex.game.theoric.algo.strat;

import hex.game.Action;
import hex.game.player.computer.Level;
import hex.game.theoric.Theory;
import hex.game.theoric.structure.Tree;

import java.io.File;
import java.util.List;

public class WinStrategy implements Theory {

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
