package hex.platform.view;

import hex.board.Board;
import hex.platform.view.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class DisplayWindow extends JFrame {

    // CONSTANTS

    private final int HEIGHT_WINDOW = 400;
    private final int WIDTH_WINDOW = 600;

    // ATTRIBUTES


    // CONSTRUCTORS

    public DisplayWindow() {
        super();
        this.updateWindow();
        this.placeComponent();
    }

    // REQUESTS

    // COMMANDS

    // UTILS

    private void updateWindow() {
        this.setSize(new Dimension(new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW)));
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }

    private void placeComponent() {
        this.createMenu();
        this.createBoard();
        this.createHelpBar();
        this.createInfoBar();
    }

    private void createInfoBar() {
    }

    private void createHelpBar() {
        this.add(new DisplayHelp(), BorderLayout.SOUTH);
    }

    private void createBoard() {
        this.add(new DisplayBoard(), BorderLayout.CENTER);
    }

    private void createMenu() {
        this.setJMenuBar(new MainMenu());
    }

    // POINT ENTERED

    public static void main(String[] args) {
        JFrame frame = new DisplayWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
