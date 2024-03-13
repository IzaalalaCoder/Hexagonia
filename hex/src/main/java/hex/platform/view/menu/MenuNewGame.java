package hex.platform.view.menu;

import hex.platform.view.info.LevelMode;
import hex.platform.view.info.PlayMode;
import hex.platform.view.info.PlayerMode;
import hex.platform.view.info.SizeGame;

import javax.swing.*;

public class MenuNewGame extends JMenu {

    public MenuNewGame() {
        super();
        this.createGameMenu();
        this.setText("Nouvelle partie");
    }

    public void createGameMenu() {
        for (PlayMode mode : PlayMode.values()) {
            JMenu menu = new JMenu(mode.name());
            menu.setIcon(MainMenu.getIcon(mode.getImg()));
            if (mode.getTypePlayers() == null) {
                this.createSizeMenu(menu);
            } else {
                for (PlayerMode playerMode : mode.getTypePlayers()) {
                    JMenu menuPlayer = new JMenu(playerMode.name());
                    menuPlayer.setIcon(MainMenu.getIcon(playerMode.getImg()));
                    this.addMenuByTypePlayer(playerMode, menuPlayer);
                    menu.add(menuPlayer);
                }
            }
            this.add(menu);
        }
    }

    private void addMenuByTypePlayer(PlayerMode playerMode, JMenu menu) {
        switch (playerMode) {
            case PLAYER -> {
                this.createSizeMenu(menu);
            }
            case COMPUTER -> {
                for (LevelMode levelMode : LevelMode.values()) {
                    JMenu levelMenu = new JMenu(levelMode.name());
                    this.createSizeMenu(levelMenu);
                    menu.add(levelMenu);

                }
            }
        }
    }

    private void createSizeMenu(JMenu menu) {
        for (SizeGame size : SizeGame.values()) {
            JMenuItem it = new JMenuItem(size.getSize().toString());
            menu.add(it);
        }
    }
}
