package hex.model.util.xml;

import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface XMLWriter {

    // CONSTANTS

    public String PATH = "hex/src/main/resources/informations/newSave.xml";

    // REQUESTS

    public File getGeneratedFile();

    // COMMANDS

    public void writeXMLFile() throws ParserConfigurationException, TransformerException ;
}
