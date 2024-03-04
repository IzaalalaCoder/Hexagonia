package hex.util.xml;

import hex.board.*;

import java.io.File;

public interface XMLWriter {

    // REQUETES

    public File getGeneratedFile();

    // COMMANDES

    public void readBoard(Board board);

    public void writeXMLFile(Board board);
}
