package hex.model.util.xml;

import hex.model.game.Game;
import hex.model.game.player.computer.Computer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WritingXML implements XMLWriter {

    // ATTRIBUTES

    private final Game model;
    private final File save;

    // CONSTRUCTOR

    public WritingXML(Game game) throws IOException {
        this.model = game;
        this.save = Files.createFile(Paths.get(PATH)).toFile();
    }

    // REQUESTS

    @Override
    public File getGeneratedFile() {
        return this.save;
    }

    // COMMANDS

    @Override
    public void writeXMLFile() throws ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.newDocument();

        // start save
        final Element root = document.createElement("save");
        document.appendChild(root);

        // append data
        this.readDataOnGame(document, root);

        // final 
        try {
            this.flush(document);
        } catch (TransformerConfigurationException e) {
            
        } catch (TransformerException e) {
        }
    }

    // UTILS

    @SuppressWarnings("unused")
    private void readBoard(Document doc, Element root) {
    }

    private void readDataOnGame(Document doc, Element root) {
        final Element data = doc.createElement("data");

        // add computer 
        if (this.model.getIsGameWithComputer()) {
            final Element computer = doc.createElement("computer");
            Computer c = (Computer) this.model.getComputer();
            computer.setAttribute("level", c.getLevel().name());
            data.appendChild(computer);
        }

        final Element current = doc.createElement("current");
        current.appendChild(doc.createTextNode(
            Integer.toString(this.model.getPositionCurrentPlayer()))
        );
        data.appendChild(current);

        root.appendChild(data);
    }

    private void flush(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(PATH));
        transformer.transform(source, result);
        
        System.out.println("Sauvegarde XML créée avec succès !");
    }
}

