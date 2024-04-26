package hex.platform.view.popup;

import javax.swing.JOptionPane;

public class ErrorPopUp {

    public static void preventStartGameError() {

        String message = "Aucune partie n'a été lancée !";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE);
    }

    public static void preventOpenOrParseFileError() {

        String message = "Le fichier n'a pas pu être ouvert ou a été corrompu";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE);
    }
}
