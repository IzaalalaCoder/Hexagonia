package hex.platform.view.menu;

import hex.platform.view.menu.game.LevelMode;
import hex.platform.view.menu.game.ModePlay;
import hex.platform.view.menu.game.TypePlayer;
import hex.platform.view.menu.game.SizeGame;

import javax.swing.*;

public class MenuNewGame extends JMenu {

    public MenuNewGame() {
        super();
        this.createGameMenu();
        this.setText("Nouvelle partie");
    }

    public void createGameMenu() {
        for (ModePlay mode : ModePlay.values()) {
            JMenu menu = new JMenu(mode.name());
            menu.setIcon(MainMenu.getIcon(mode.getImg()));
            if (mode.getTypePlayers() == null) {
                this.createSizeMenu(menu);
            } else {
                for (TypePlayer typePlayer : mode.getTypePlayers()) {
                    JMenu menuPlayer = new JMenu(typePlayer.name());
                    menuPlayer.setIcon(MainMenu.getIcon(typePlayer.getImg()));
                    this.addMenuByTypePlayer(typePlayer, menuPlayer);
                    menu.add(menuPlayer);
                }
            }
            this.add(menu);
        }
    }

    private void addMenuByTypePlayer(TypePlayer typePlayer, JMenu menu) {
        switch (typePlayer) {
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
