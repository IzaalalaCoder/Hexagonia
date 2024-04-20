package hex.model.game.theoric.algo.max;

import hex.model.game.Action;
import hex.model.game.player.computer.Level;
import hex.model.game.theoric.AbstractTheory;
import hex.model.game.theoric.structure.node.ManageNode;
import hex.model.game.theoric.structure.Tree;

import java.io.File;
import java.util.List;

/**
 * - Dessiner une arborescence de racine r
 * - Associer à chacune des feuilles de l'arborescence un nombre
 * positif ou négatif ou nul
 * - Ce nombre représente les points gagnés par Max (le premier joueur)
 *     < 0 : Max a perdu et Min a gagné
 *     > 0 : Max a gagné et Min a perdu
 *     = 0 : Max et Min ont gagné
 */
public class Minimax extends AbstractTheory {

    // ATTRIBUTES

    // CONSTRUCTORS

    // REQUESTS

    public int getMinimaxValue() {
        return 0;
    }

    public List<ManageNode> getAllTerminalNode() {
        return null;
    }

    public List<ManageNode> getAllNodePreviousTerminalNodes() {
        return null;
    }

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

    public void setMinimaxValue() {

    }

    public void initializeAssociation() {

    }

    public void setMinimaxValueAtNode(int minimaxValue) {

    }
}
