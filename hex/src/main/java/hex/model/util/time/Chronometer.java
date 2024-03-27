package hex.model.util.time;

import hex.platform.controller.ControlChronometer;

import javax.swing.*;
import java.util.Map;

public class Chronometer implements ManageTimer {

    // ATTRIBUTES

    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private long startTime;
    private final Timer timer;

    // CONSTRUCTORS

    public Chronometer() {
        this.timer = new Timer(1,
                new ControlChronometer(this));
    }

    public Chronometer(int h, int m, int s) {
        this.setTime(h, m, s);
        this.timer = new Timer(1,
                new ControlChronometer(this));
    }

    // REQUESTS

    public Timer getTimer() {
        return timer;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d.%02d", hours, minutes, seconds);
    }

    // COMMANDS

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setTime(int h, int m, int s) {
        this.setHours(h);
        this.setMinutes(m);
        this.setSeconds(s);

    }

    public void calibrate() {
        startTime = System.currentTimeMillis() - (hours * 3600000L)
                - (minutes * 60000L) - (seconds * 1000L);
    }

}