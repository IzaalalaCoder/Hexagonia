package hex.util.xml;

import java.io.File;

import hex.game.*;

public interface XMLParser {

    // REQUETES

    public Game getGameInFile();

    // COMMANDES

    public void readFileXML(File f);
}
