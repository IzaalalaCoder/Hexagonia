package hex.platform.view.shapes;

import java.awt.*;

public interface Forms {

    // CONSTANTS

    public final int SIZE_DEFAULT = 50;

    // REQUESTS

    boolean isContainedInForm(Point p);

    int getOrdinate();

    int getAbscissa();

    // COMMANDS

    void changeColor(Color c);

    void removeController();
}
