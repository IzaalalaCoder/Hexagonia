package hex.platform.view.menu;

import hex.platform.view.menu.game.ModePlayer;
import hex.platform.view.menu.game.SizeGame;

import javax.swing.*;

public class MenuNewGame extends JMenu {

    public MenuNewGame() {
        super();
        this.setText("Nouvelle partie");
        this.createGameMenu();
    }

    public void createGameMenu() {
        for (ModePlayer player : ModePlayer.values()) {
            JMenu menu = new JMenu(player.name());
            menu.setIcon(MainMenu.getIcon(player.getImg()));
            for (SizeGame size : SizeGame.values()) {
                JMenuItem it = new JMenuItem(size.getSize().toString());
                menu.add(it);
            }
            this.add(menu);
        }

    }
}
