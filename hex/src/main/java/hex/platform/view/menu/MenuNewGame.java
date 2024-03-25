package hex.platform.view.menu;

import hex.game.Game;
import hex.platform.view.DisplayWindow;
import hex.platform.view.info.LevelMode;
import hex.platform.view.info.PlayMode;
import hex.platform.view.info.PlayerMode;
import hex.platform.view.info.SizeGame;
import hex.platform.view.menu.util.InformationOnNewGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuNewGame extends JMenu {

    private final List<Pair<InformationOnNewGame, JMenuItem>> menus;

    public MenuNewGame() {
        super();
        this.menus = new ArrayList<>();
        this.createGameMenu();
        this.setText("Nouvelle partie");
    }

    public void createController(DisplayWindow parent)  {
        for (Pair<InformationOnNewGame, JMenuItem> pair : this.menus) {
            InformationOnNewGame information = pair.getFirst();
            JMenuItem it = pair.getSecond();

            boolean isComputer = information.isComputer();
            boolean isOffline = information.isOffline();
            int level = information.getLevel();
            int size = Integer.parseInt(it.getText());
            Game model = new Game(
                    isOffline,
                    isComputer,
                    level,
                    size
            );

            it.addActionListener(new ActionListener() {
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
                InformationOnNewGame info = new InformationOnNewGame(false);
                info.setComputer(false);
                this.createSizeMenu(info, menu);
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

    private void addMenuByTypePlayer(PlayerMode playerMode, JMenu submenu) {

        if (playerMode == PlayerMode.PLAYER) {
            InformationOnNewGame info = new InformationOnNewGame(true);
            info.setComputer(false);
            this.createSizeMenu(info, submenu);
        } else {
            for (LevelMode levelMode : LevelMode.values()) {
                InformationOnNewGame info = new InformationOnNewGame(true);
                info.setComputer(true);
                JMenu levelMenu = new JMenu(levelMode.name());
                info.setLevel(levelMode.ordinal());
                this.createSizeMenu(info, levelMenu);
                submenu.add(levelMenu);
            }
        }
    }

    private void createSizeMenu(InformationOnNewGame information, JMenu menu) {
        for (SizeGame size : SizeGame.values()) {
            JMenuItem it = new JMenuItem(String.valueOf(size.getSize()));
            this.menus.add(new Pair<>(information, it));
            menu.add(it);
        }
    }

    private static class Pair<V, W> {
        private final V first;
        private final W second;

        public Pair(V first, W second) {
            this.first = first;
            this.second = second;
        }

        public V getFirst() {
            return first;
        }

        public W getSecond() {
            return second;
        }
    }
}
