package hex.platform.view;

import javax.swing.*;

public class DisplayHelp extends JPanel {

    private final JTextArea area = new JTextArea();

    public DisplayHelp() {
        super();
        area.setRows(5);
        area.setColumns(800);
        this.placeComponents();
    }

    private void placeComponents() {
        this.add(this.area);
    }
}
