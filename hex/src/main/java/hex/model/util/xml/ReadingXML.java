package hex.model.util.xml;

import hex.model.game.Game;

import java.io.File;

public class ReadingXML implements XMLScheme, XMLParser {

    // ATTRIBUTS

    @SuppressWarnings("unused")
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
