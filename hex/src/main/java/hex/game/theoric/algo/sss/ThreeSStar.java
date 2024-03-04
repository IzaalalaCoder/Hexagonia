package hex.game.theoric.algo.sss;

import hex.game.Action;
import hex.game.player.computer.Level;
import hex.game.theoric.Theory;
import hex.game.theoric.structure.Tree;
import hex.util.structure.file.ManageFile;
import hex.util.structure.file.PriorityFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ThreeSStar implements Theory {

    // ATTRIBUTES

    private final ManageFile<Element> priorityFile;
    private final Tree tree;
    private final List<Action> possibleActions;

    // CONSTRUCTORS

    public ThreeSStar(Tree t) {
        this.priorityFile = new PriorityFile<Element>();
        this.tree = t;
        this.possibleActions = new ArrayList<Action>();
    }

    // REQUESTS

    @Override
    public Tree getTree() {
        return this.tree;
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
        return this.possibleActions;
    }

    // COMMANDS

    @Override
    public void analysisByLevel(Level level) {

    }

    @Override
    public void analysisForPlayer() {

    }

    // UTILS

    private void initializePriorityFile() {
    }

}
