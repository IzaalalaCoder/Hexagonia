package hex.game.theoric;

import hex.game.Action;
import hex.game.player.computer.Level;
import hex.game.theoric.structure.Tree;

import java.io.File;
import java.util.List;

public interface Theory {

    // REQUEST

    public Tree getTree();

    /**
     * @return return explains that possible action of player.
     */
    public String getExplain();

    /**
     * @return get file with explains on basic subject
     */
    public File getBasicAnalysis();

    public List<Action> getEndAnalysis();

    // COMMANDS

    void analysisByLevel(Level level);

    void analysisForPlayer();

}
