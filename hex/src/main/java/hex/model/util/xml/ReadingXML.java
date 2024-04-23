package hex.model.util.xml;

import hex.model.game.Game;
import hex.model.game.player.AbstractPlayer;
import hex.model.game.player.computer.Level;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadingXML implements XMLScheme, XMLParser {

    // ATTRIBUTES

    private Document document;
    private Game model;
    private File file;
    private boolean flag = true;

    // CONSTRUCTOR

    public ReadingXML(File f) throws IOException {
        this.model = null;
        this.document = null;
        this.file = Files.copy(f.toPath(), Paths.get("hex/src/main/resources/informations/save.xml")).toFile();
        this.flag = true;
        try {
            this.openFile();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.flag = false;
        }
    }

    // REQUESTS

    public Game getGameInFile() {
        if (!this.flag) {
            throw new AssertionError("Can not create game");
        }
        return this.model;
    }

    @Override
    public boolean checkXMLFile() {
        return this.flag;
    }

    // COMMANDS

    @Override
    public void readFileXML() throws IOException {
        if (!this.flag) {
            throw new AssertionError("Can not open");
        }

        System.out.println("rentré");
        
        this.browseFile(file);

        Files.delete(this.file.toPath());
    }

    // UTILS

    private void openFile() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                throw exception;
            }
            
        });
        this.document = builder.parse(file);
    }


    private void browseFile(File file) {
        final Element dataElement = (Element) this.document
            .getElementsByTagName("data").item(0);

        NodeList computerList = dataElement.getElementsByTagName("computer");
        Level level = computerList.getLength() == 0 ? 
            null 
            : this.getLevel(computerList.item(0).getAttributes()
            .getNamedItem("level").getTextContent());
        
        int current = Integer.parseInt(dataElement.getElementsByTagName("current")
            .item(0).getTextContent());

        NodeList rowList = this.document.getElementsByTagName("row");
        this.model = new Game(computerList.getLength() != 0, level.ordinal(), rowList.getLength());
        this.model.setCurrentPlayer(current);

        this.browseBoard(rowList);
    }

    private void browseBoard(NodeList rowList) {
        for (int i = 0; i < rowList.getLength(); i++) {
            Node rowNode = rowList.item(i);
            if (rowNode.getNodeType() == Node.ELEMENT_NODE) {
                Element rowElement = (Element) rowNode;
                NodeList cellList = rowElement.getElementsByTagName("cell");
                for (int j = 0; j < cellList.getLength(); j++) {
                    String content = cellList.item(j).getTextContent();
                    if (content.length() == 1) {
                        AbstractPlayer p = this.model.getPlayers()[Integer.parseInt(content)];
                        this.model.getBoard().getGrid()[i][j].setPlayer(p);
                    }
                }
            }
        }
    }

    private Level getLevel(String levelString) {
        Level l = null;
        for (Level level : Level.values()) {
            if (level.name().equals(levelString)) {
                l = level;
                break;
            }
        }
        return l;
    }
}
