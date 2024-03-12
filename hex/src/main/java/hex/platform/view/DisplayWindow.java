package hex.platform.view;

import hex.board.Board;
import hex.platform.view.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class DisplayWindow extends JFrame {

    // CONSTANTS


    int WIDTH_WINDOW = 1000;

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
        int HEIGHT_WINDOW = 650;
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
        this.add(new DisplayInfo(), BorderLayout.NORTH);
    }

    private void createHelpBar() {
        DisplayHelp help = new DisplayHelp();
        help.setPreferredSize(new Dimension(WIDTH_WINDOW, 100));
        this.add(help, BorderLayout.SOUTH);
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
