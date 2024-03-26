package hex.platform.view.menu.util;

import hex.model.game.player.computer.Level;

public class InformationOnNewGame {
    private Boolean isComputer;
    private final Boolean isOffline;
    private int level;

    public InformationOnNewGame(boolean offline) {
        this.isComputer = null;
        this.isOffline = offline;
        this.level = -1;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Offline : " + isOffline + " Computer : " + isComputer
                + " Level : " + (level != -1 ? Level.values()[level].name() : 0);
    }
}