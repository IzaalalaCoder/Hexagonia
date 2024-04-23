package hex.model.util.xml;

import java.io.IOException;

import hex.model.game.Game;

public interface XMLParser {

    // CONSTANTS

    public String PATH_DTD = "hex/src/main/resources/informations/save.dtd";
    public final String PATH_XML = "hex/src/main/resources/informations/";

    // REQUESTS

    public Game getGameInFile();

    // COMMANDS

    public void readFileXML() throws IOException;
}
