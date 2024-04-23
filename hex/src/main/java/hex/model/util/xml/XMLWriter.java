package hex.model.util.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface XMLWriter {

    // CONSTANTS

    public String PATH_XML = "hex/src/main/resources/informations/newSave.xml";

    // REQUESTS

    public File getGeneratedFile() throws IOException;

    // COMMANDS

    public void writeXMLFile() throws ParserConfigurationException, TransformerException ;
}
