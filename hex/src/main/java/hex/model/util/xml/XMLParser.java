package hex.model.util.xml;

import java.io.IOException;

import hex.model.game.Game;

public interface XMLParser {

    // CONSTANTS

    public final String PATH = "hex/src/main/resources/informations/";

    // REQUESTS

    public Game getGameInFile();

    // COMMANDS

    public void readFileXML() throws IOException;
}
