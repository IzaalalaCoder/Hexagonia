package hex.platform.view.shapes;

import hex.model.board.cell.Direction;

import java.awt.*;
import java.util.Map;

public interface Forms {

    // CONSTANTS

    public final int SIZE_DEFAULT = 50;
    public final Color BG_COLOR_HOVER = Color.MAGENTA;
    public final Color BORDER_COLOR_DEFAULT = Color.BLACK;

    // REQUESTS

    boolean isContainedInForm(Point p);

    int getOrdinate();

    int getAbscissa();

    // COMMANDS

    void changeColor(Color c);

    void removeController();
}
