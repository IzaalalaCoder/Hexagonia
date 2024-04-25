package hex.platform.view.popup;

import javax.swing.JOptionPane;

public class ErrorPopUp {

    public static void preventError() {

        String message = "Aucune partie n'a été lancée !";

        JOptionPane.showMessageDialog(null, message, "Erreur"
            , JOptionPane.ERROR_MESSAGE);
    }
}
