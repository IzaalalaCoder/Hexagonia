package hex.platform.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import hex.model.game.Game;

public class ControlLoadGame implements ActionListener {

    // ATTRIBUTES 
    
    private Game game;
    
    // CONSTRUCTORS

    public ControlLoadGame(Game game, String path) {
        this.game = game;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    

    public static void main(final String[] args) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document= builder.parse(new File("hex/src/main/resources/informations/example_save.xml"));
                
            System.out.println("*************PROLOGUE************");
            System.out.println("version : " + document.getXmlVersion());
            System.out.println("encodage : " + document.getXmlEncoding());        
            System.out.println("standalone : " + document.getXmlStandalone());

            final Element racine = document.getDocumentElement();
            System.out.println("\n*************RACINE************");
            System.out.println(racine.getNodeName());
             
        } catch (final ParserConfigurationException e)   {}
        catch (final SAXException e)  {}
        catch (final IOException e)  {}       
    }

}
