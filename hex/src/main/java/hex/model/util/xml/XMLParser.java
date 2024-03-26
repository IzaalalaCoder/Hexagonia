package hex.model.util.xml;

import java.io.File;

import hex.model.game.Game;

public interface XMLParser {

    // REQUETES

    public Game getGameInFile();

    // COMMANDES

    public void readFileXML(File f);
}
