package hex.model.util.xml;

import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Game;
import hex.model.game.player.computer.Computer;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class WritingXML implements XMLWriter {

    // ATTRIBUTES

    private final Game model;
    private File save;

    // CONSTRUCTOR

    public WritingXML(Game game) {
        this.model = game;
        this.save = null;
    }

    // REQUESTS

    @Override
    public File getGeneratedFile() {
        return this.save;
        //return Files.copy(this.save.toPath(), Paths.get(PATH + "finalSave.xml")).toFile();
    }

    // COMMANDS

    @Override
    public void writeXMLFile() throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        
        String qualifiedName = "save";
        String systemId = "save.dtd";
        DocumentType doctype = document.getImplementation().createDocumentType(qualifiedName, null, systemId);
        document.appendChild(doctype);

        // Création de l'élément racine
        Element root = document.createElement("save");
        document.appendChild(root);
        
        final Element data = document.createElement("data");

        // add computer 
        if (this.model.getIsGameWithComputer()) {
            final Element computer = document.createElement("computer");
            Computer c = (Computer) this.model.getComputer();
            computer.setAttribute("level", c.getLevel().name());
            data.appendChild(computer);
        }

        final Element current = document.createElement("current");
        current.appendChild(document.createTextNode(
            Integer.toString(this.model.getPositionCurrentPlayer()))
        );
        data.appendChild(current);

        final Element end = document.createElement("end");
        end.appendChild(document.createTextNode(
            this.model.isEndOfGame() ? "1" : "0" 
        ));
        data.appendChild(end);

        root.appendChild(data);
        
        // Ajout de l'élément "board" avec ses sous-éléments
        Element boardElement = document.createElement("board");
        root.appendChild(boardElement);
        
        // Ajout des lignes et des cellules de la grille
        
        for (Cell[] cells : this.model.getBoard().getGrid()) {
            Element rowElement = document.createElement("row");
            boardElement.appendChild(rowElement);
            for (Cell c : cells) {
                Element cellElement = document.createElement("cell");
                
                cellElement.appendChild(document.createTextNode(c.getState() != State.EMPTY ? Integer.toString(c.getPlayer().getPosition()) : ""));
                rowElement.appendChild(cellElement);
            }
        }
        
        // Écriture du contenu dans un fichier XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        this.save = new File(PATH_XML);
        StreamResult result = new StreamResult(save);

        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");      
        transformer.setOutputProperty("doctype-system", "save.dtd");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


        transformer.transform(source, result);
    }
    
}

