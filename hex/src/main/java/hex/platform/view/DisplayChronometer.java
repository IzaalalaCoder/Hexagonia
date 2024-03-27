package hex.platform.view;

import hex.model.util.time.Chronometer;

import javax.swing.*;
import java.awt.*;

public class DisplayChronometer extends JButton {

    // ATTRIBUTES

    private Chronometer chronometer;

    // CONSTRUCTORS

    public DisplayChronometer() {
        this.createModel();
        this.placeComponents();
        this.createController();
    }

    private void placeComponents() {
        setFont(new Font("Consolas", Font.PLAIN, 13));
        setText(chronometer.toString());
    }

    private void createModel() {
        this.chronometer = new Chronometer();
    }

    private void createController() {
        this.addPropertyChangeListener(evt -> changeText());
    }

    private void changeText() {
        switch (this.getText()) {
            case "Pause":
                chronometer.getTimer().stop();
                setText("Continuer");
                break;
            case "Continuer":
                chronometer.calibrate();
                chronometer.getTimer().start();
                setText("Pause");
                break;
            default:
                break;
        }
    }


}
