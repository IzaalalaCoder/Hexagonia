package hex.platform.view.shapes;

import hex.board.cell.Direction;
import hex.board.cell.Shape;
import hex.game.player.PlayerName;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public interface Forms {

    // CONSTANTS

    public final int SIZE_DEFAULT_FOR_ROUND = 100;
    public final int SIZE_DEFAULT_FOR_HEX = 150;
    public final Color BG_COLOR_HOVER = Color.MAGENTA;
    public final Color BORDER_COLOR_DEFAULT = Color.BLACK;

    // REQUESTS

    Shape getShapeOfJButton();

    boolean isContainedInForm(Point p);

    String getCoordinate();

    // COMMANDS

    void changeColor(Color c);

    void setColorsForBorder(Map<Direction, Color> c);

    void changeController();
}
