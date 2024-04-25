package hex.platform.view.popup;

import javax.swing.JOptionPane;

public class WarningPopUp {

    public static int preventSaveAble() {

        String message = "Etes-vous sûr de supprimer la partie en cours ?";

        return JOptionPane.showConfirmDialog(null, message, "Attention !"
            , JOptionPane.YES_NO_OPTION);
    }

}
