package hex.model.util.xml;

import hex.model.board.Board;

import java.io.File;

public interface XMLWriter {

    public String PATH = "hex/src/main/resources/informations";

    // REQUETES

    public File getGeneratedFile();

    // COMMANDES

    public void readBoard(Board board);

    public void writeXMLFile(Board board);
}
