package hex.platform.view;

import hex.game.Game;
import hex.platform.view.info.SizeGame;
import hex.platform.view.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class DisplayWindow extends JFrame {

    // CONSTANTS

    // ATTRIBUTES

    private final Game model;
    private final JPanel help;
    private final JPanel board;
    private final JPanel information;

    // CONSTRUCTORS

    public DisplayWindow() {
        super();
        this.model = new Game(3);
        this.board = new DisplayBoard(this.model.getBoard());
        this.help = new DisplayHelp();
        this.information = new DisplayInfo();
        this.updateWindow();
        this.placeComponent();
    }

    // REQUESTS

    // COMMANDS

    // UTILS

    private void updateWindow() {
        this.setResizable(false);
        this.resizeWindow();
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }

    private void resizeWindow() {
        for (SizeGame size : SizeGame.values()) {
            if (model.getSize() == size.getSize()) {
                this.setSize(size.getDimension());
            }
        }
    }

    private void placeComponent() {
        this.createMenu();
        this.createBoard();
        this.createHelpBar();
        this.createInfoBar();
    }

    private void createInfoBar() {
        this.add(this.information, BorderLayout.NORTH);
    }

    private void createHelpBar() {
        //this.help.setPreferredSize(new Dimension(WIDTH_WINDOW, 100));
        this.add(help, BorderLayout.SOUTH);
    }

    private void createBoard() {
        this.add(this.board, BorderLayout.CENTER);
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
