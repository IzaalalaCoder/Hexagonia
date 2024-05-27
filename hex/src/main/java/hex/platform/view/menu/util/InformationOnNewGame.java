package hex.platform.view.menu.util;

import hex.model.game.player.computer.Level;

public class InformationOnNewGame {

    // ATTRIBUTES

    private Boolean isComputer;
    private int level;

    // CONSTRUCTOR

    public InformationOnNewGame() {
        this.isComputer = null;
        this.level = -1;
    }

    // REQUESTS

    public boolean isComputer() {
        return isComputer;
    }

    public int getLevel() {
        return level;
    }
    
    @Override
    public String toString() {
        return  " Computer : " + isComputer
                + " Level : " + (level != -1 ? Level.values()[level].name() : 0);
    }

    // COMMANDS

    public void setLevel(int level) {
        this.level = level;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

}