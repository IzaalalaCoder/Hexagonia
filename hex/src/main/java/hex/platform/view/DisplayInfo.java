package hex.platform.view;

import javax.swing.*;
import java.awt.*;

public class DisplayInfo extends JPanel {

    public DisplayInfo() {
        super();
        this.setLayout(new GridLayout(0, 3));
        this.setPreferredSize(new Dimension(1000, 30));
        placeComponents();
    }

    private void placeComponents() {
        this.add(new JButton("Montre"));
        this.add(new JLabel("Joueur 1"));    }


}
