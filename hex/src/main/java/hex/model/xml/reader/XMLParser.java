package hex.model.xml.reader;

import java.io.IOException;
import hex.model.game.Game;

public interface XMLParser {

    // CONSTANTS

    String PATH_XML = "src/main/resources/informations/";

    // REQUESTS

    Game getGameInFile();

    // COMMANDS

    void readFileXML() throws IOException;
}