package hex.platform.view.shapes;

import hex.board.cell.Direction;
import hex.board.cell.Shape;

import java.awt.*;
import java.util.Map;

public interface Forms {

    // CONSTANTS

    public final int SIZE_DEFAULT = 50;
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
