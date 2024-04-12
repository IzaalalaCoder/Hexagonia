package hex.platform.view;

import hex.model.game.Game;
import hex.platform.view.info.SizeGame;
import hex.platform.view.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class DisplayWindow extends JFrame {

    // CONSTANTS

    private final Dimension DIMENSION = new Dimension(600, 600);

    // ATTRIBUTES

    private Game model;
    private JPanel help;
    private DisplayBoard board;
    private JPanel information;
    private MainMenu mainMenu;

    // CONSTRUCTORS

    public DisplayWindow() {
        super();
        this.createMenu();
        this.createController();
        this.setSize(this.DIMENSION);
        this.setLayout(new BorderLayout());
        this.updateWindow();
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
        if (!model.getIsGameWithComputer()) {
            this.information = new DisplayInfo(this.model);
        }
        this.board.setVisible(true);
        //this.help = new DisplayHelp();
        this.placeComponent();
        this.updateWindow();
    }

    private void removeAllController() {
        if (this.board != null) {
            this.board.removeAllController();
            this.board.setVisible(false);
            this.remove(this.board);
            //this.remove(this.help);
            if (!model.getIsGameWithComputer()) {
                this.information.setVisible(false);
                this.remove(this.information);
            }

        }
    }

    private void placeComponent() {
        this.createBoard();
        //this.createHelpBar();

        if (!model.getIsGameWithComputer()) {
            this.createInfoBar();
        }

    }

    private void createInfoBar() {
        this.add(this.information, BorderLayout.NORTH);
    }

    private void createHelpBar() {
        this.add(help, BorderLayout.SOUTH);
    }

    private void createBoard() {
        this.add(this.board, BorderLayout.CENTER);
    }

    private void createMenu() {
        this.createContents();
        this.setJMenuBar(this.mainMenu);
    }

    // POINT ENTERED

    public static void main(String[] args) {
        JFrame frame = new DisplayWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
