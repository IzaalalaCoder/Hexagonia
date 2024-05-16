package hex.platform.view;

import hex.model.game.AbstractGame;
import hex.model.game.Game;
import hex.platform.view.info.PlayerName;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class DisplayInfo extends JPanel {

    // ATTRIBUTES

    private Game model;
    private JLabel playerInformation;

    // CONSTRUCTORS

    public DisplayInfo(Game model) {
        super();
        this.createModel(model);
        this.createComponents();
        this.placeComponents();
        this.createController();
    }

    // UTILS

    private void createModel(Game model) {
        this.model = model;
    }

    private void createComponents() {
        this.playerInformation = new JLabel();
        playerInformation.setText("Player " + PlayerName.FIRST.name());
    }

    private void placeComponents() {
        this.add(this.playerInformation);

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(1000, 30));
    }

    private void createController() {
        this.model.addPropertyChangeListener(AbstractGame.PROP_CURR_PLAYER_ID, evt -> {
            int value = (int) evt.getNewValue();
            PlayerName playerName = PlayerName.values()[value];
            playerInformation.setText("Player " + playerName.name());
        });
    }
}