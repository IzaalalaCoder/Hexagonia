package hex.platform.view.menu;

import hex.platform.controller.menu.ControlMenuNewGame;
import hex.platform.view.DisplayWindow;
import hex.platform.view.info.LevelMode;
import hex.platform.view.info.PlayerMode;
import hex.platform.view.info.SizeGame;
import hex.platform.view.menu.util.InformationOnNewGame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuNewGame extends JMenu {

    // ATTRIBUTES 
    
    private final List<Pair<InformationOnNewGame, JMenuItem>> menus;

    // CONSTRUCTORS

    public MenuNewGame() {
        super();
        this.menus = new ArrayList<>();
        this.createGameMenu();
        this.setText("Nouvelle partie");
    }

    // COMMANDS

    public void createController(DisplayWindow parent)  {
        for (Pair<InformationOnNewGame, JMenuItem> pair : this.menus) {
            InformationOnNewGame information = pair.getFirst();
            JMenuItem it = pair.getSecond();
            boolean isComputer = information.isComputer();
            int level = information.getLevel();
            int size = Integer.parseInt(it.getText());
            it.addActionListener(new ControlMenuNewGame(parent, isComputer, level, size));
        }
    }

    public void createGameMenu() {
        for (PlayerMode playerMode : PlayerMode.values()) {
            JMenu menuPlayer = new JMenu(playerMode.name());
            menuPlayer.setIcon(MainMenu.getIcon(playerMode.getImg()));
            this.addMenuByTypePlayer(playerMode, menuPlayer);
            this.add(menuPlayer);
        }
    }

    // UTILS

    private void addMenuByTypePlayer(PlayerMode playerMode, JMenu submenu) {
        if (playerMode == PlayerMode.PLAYER) {
            InformationOnNewGame info = new InformationOnNewGame();
            info.setComputer(false);
            this.createSizeMenu(info, submenu);
        } else {
            for (LevelMode levelMode : LevelMode.values()) {
                InformationOnNewGame info = new InformationOnNewGame();
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

    // IMBRICATION CLASSES
    
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