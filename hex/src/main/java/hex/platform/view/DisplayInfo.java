package hex.platform.view;

import hex.game.AbstractGame;
import hex.game.Game;
import hex.platform.view.info.PlayerName;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayInfo extends JPanel {

    private Game model;
    private JLabel playerInformation;

    public DisplayInfo(Game model) {
        super();
        this.playerInformation = new JLabel();
        playerInformation.setText("Player " + PlayerName.FIRST.name());
        this.model = model;
        this.setLayout(new GridLayout(0, 3));
        this.setPreferredSize(new Dimension(1000, 30));
        this.placeComponents();
        this.createController();
    }

    private void placeComponents() {
        this.add(new JButton("Montre"));
        this.add(this.playerInformation);
    }

    private void createController() {
        this.model.addPropertyChangeListener(AbstractGame.PROP_CURR_PLAYER, new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int value = (int) evt.getNewValue();
                PlayerName playerName = PlayerName.values()[value];
                playerInformation.setText("Player " + playerName.name());
            }
        });
    }
}
