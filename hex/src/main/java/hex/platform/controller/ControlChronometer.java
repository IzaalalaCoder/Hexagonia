package hex.platform.controller;

import hex.model.util.time.Chronometer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControlChronometer implements ActionListener {

    // ATTRIBUTES

    private final Chronometer chronometer;

    // CONSTRUCTORS

    public ControlChronometer(Chronometer c) {
        this.chronometer = c;
    }

    // COMMANDS

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTime();
    }

    public void updateTime() {
        long diff = System.currentTimeMillis() - chronometer.getStartTime();

        int seconds = (int) ((diff / 1000) % 60);
        int minutes = (int) ((diff / 1000) / 60 % 60);
        int hours = (int) ((diff / 1000) / 60 / 60);

        chronometer.setTime(hours, minutes, seconds);
    }
}