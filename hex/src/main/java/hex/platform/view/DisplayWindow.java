package hex.platform.view;

import hex.game.Game;
import hex.platform.view.info.SizeGame;
import hex.platform.view.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class DisplayWindow extends JFrame {

    // CONSTANTS

    private Dimension dim = new Dimension(600, 600);

    // ATTRIBUTES

    private Game model;
    private JPanel help;
    private JPanel board;
    private JPanel information;

    private MainMenu mainMenu;

    // CONSTRUCTORS

    public DisplayWindow() {
        super();
        this.createContents();
        this.createMenu();
        this.help = new DisplayHelp();
        this.updateWindow();
        this.createController();
        this.setSize(this.dim);
    }

    // REQUESTS

    // COMMANDS

    // UTILS

    private void createContents() {
        this.mainMenu = new MainMenu(this);
    }

    private void updateWindow() {
        this.setResizable(false);
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

    private void createController() {

    }

    public void setModel(Game game) {
        this.removeAllController();
        this.model = game;
        this.resizeWindow();
        this.board = new DisplayBoard(this.model);
        this.information = new DisplayInfo(this.model);

        this.placeComponent();
    }

    private void removeAllController() {

    }

    private void placeComponent() {
        if (this.board != null) {
            this.createBoard();
        }
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
        this.setJMenuBar(this.mainMenu);
    }

    // POINT ENTERED

    public static void main(String[] args) {
        JFrame frame = new DisplayWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
