package hex.platform.view.popup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import hex.platform.view.menu.MainMenu;

public class EndGamePopUp {
    
    public static void preventEndGame(String message, boolean isLost) {
        ImageIcon icon = new ImageIcon(MainMenu.PATH + (isLost ? "lost.png" : "win.png"));

        JOptionPane.showMessageDialog(null, message, "Fin de la partie"
            , JOptionPane.INFORMATION_MESSAGE, icon);
    }

}
