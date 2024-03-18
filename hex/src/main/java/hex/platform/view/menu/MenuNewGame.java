package hex.platform.view.menu;

import hex.game.Game;
import hex.platform.view.DisplayWindow;
import hex.platform.view.info.LevelMode;
import hex.platform.view.info.PlayMode;
import hex.platform.view.info.PlayerMode;
import hex.platform.view.info.SizeGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MenuNewGame extends JMenu {

    private Map<InformationOnNewGame, JMenuItem> menus;

    public MenuNewGame() {
        super();
        this.menus = new HashMap<>();
        this.createGameMenu();
        this.setText("Nouvelle partie");
    }

    public void createController(DisplayWindow parent)  {
        for (InformationOnNewGame information : this.menus.keySet()) {
            boolean isComputer = information.isComputer();
            boolean isOffline = information.isOffline();
            LevelMode level = information.getLevel();
            Game model = new Game(isOffline, isComputer, level.ordinal(), information.getSize());
            this.menus.get(information).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.setModel(model);
                }
            });
        }
    }

    public void createGameMenu() {
        for (PlayMode mode : PlayMode.values()) {
            JMenu menu = new JMenu(mode.name());
            menu.setIcon(MainMenu.getIcon(mode.getImg()));
            if (mode.getTypePlayers() == null) {
                this.createSizeMenu(new InformationOnNewGame(false), menu);
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
        InformationOnNewGame info = new InformationOnNewGame(true);
        info.setComputer(PlayerMode.COMPUTER == playerMode);
        if (playerMode == PlayerMode.PLAYER) {
            this.createSizeMenu(info, menu);
        } else {
            for (LevelMode levelMode : LevelMode.values()) {
                JMenu levelMenu = new JMenu(levelMode.name());
                info.setLevel(levelMode);
                this.createSizeMenu(info, levelMenu);
                menu.add(levelMenu);

            }
        }
    }

    private void createSizeMenu(InformationOnNewGame information, JMenu menu) {
        for (SizeGame size : SizeGame.values()) {
            JMenuItem it = new JMenuItem(size.getSize().toString());
            information.setSize(size.getSize());
            menu.add(it);
        }
    }

    private static class InformationOnNewGame {
        private Boolean isComputer;
        private final Boolean isOffline;
        private LevelMode level;
        private Integer size;

        public InformationOnNewGame(boolean offline) {
            this.isComputer = null;
            this.isOffline = offline;
            this.level = null;
            this.size = null;
        }

        public boolean isComputer() {
            return isComputer;
        }

        public void setComputer(boolean computer) {
            isComputer = computer;
        }

        public boolean isOffline() {
            return isOffline;
        }

        public void setLevel(LevelMode level) {
            this.level = level;
        }

        public LevelMode getLevel() {
            return level;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
