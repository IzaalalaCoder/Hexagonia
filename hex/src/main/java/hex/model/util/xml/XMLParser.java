package hex.model.util.xml;

import java.io.IOException;

import hex.model.game.Game;

public interface XMLParser {

    public final String PATH = "hex/src/main/resources/informations/";

    // REQUETES

    public Game getGameInFile();

    // COMMANDES

    public void readFileXML() throws IOException;
}
