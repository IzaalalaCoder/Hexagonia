package hex.model.game.theoric.algo.sss;

import hex.model.game.Action;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.AbstractTheory;
import hex.model.game.theoric.structure.Tree;
import hex.model.util.structure.file.ManageFile;
import hex.model.util.structure.file.PriorityFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ThreeSStar extends AbstractTheory {

    // ATTRIBUTES

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    private void initializePriorityFile() {
    }

}
