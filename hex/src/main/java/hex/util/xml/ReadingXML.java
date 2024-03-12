package hex.util.xml;

import hex.board.*;
import hex.game.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadingXML implements XMLScheme, XMLParser {

    // ATTRIBUTS

    private File f;

    // CONSTRUCTEUR

    public ReadingXML(File f) {
        this.f = f;
    }

    // REQUETES

    public Game getGameInFile() {
        return null;
    }

    @Override
    public boolean checkXMLFile(File file) {
        return false;
    }

    // COMMANDES

    @Override
    public void readFileXML(File f) {
    }
}
