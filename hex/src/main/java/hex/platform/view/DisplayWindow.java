package hex.platform.view;

import hex.model.game.Game;
import hex.platform.view.info.SizeGame;
import hex.platform.view.menu.MainMenu;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class DisplayWindow extends JFrame {

    // ATTRIBUTES

    private Game model;
    private DisplayBoard board;
    private DisplayInfo information;
    private MainMenu mainMenu;

    // CONSTRUCTORS

    public DisplayWindow() {
        super();
        this.createMenu();
        Dimension DIMENSION = new Dimension(600, 600);
        this.setSize(DIMENSION);
        this.setLayout(new BorderLayout());
        this.updateWindow();
    }
    
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

    public void setModel(Game game) {
        this.removeAllController();
        this.model = game;
        this.resizeWindow();
        this.board = new DisplayBoard(this.model);
        if (!model.getIsGameWithComputer()) {
            this.information = new DisplayInfo(this.model);
        }
        this.board.setVisible(true);
        this.placeComponent();
        this.updateWindow();
    }

    private void removeAllController() {
        if (this.board != null) {
            this.board.removeAllController();
            this.board.setVisible(false);
            this.remove(this.board);
            if (!model.getIsGameWithComputer()) {
                this.information.setVisible(false);
                this.remove(this.information);
            }
        }
    }

    private void placeComponent() {
        this.createBoard();
        if (!model.getIsGameWithComputer()) {
            this.createInfoBar();
        }
    }

    private void createInfoBar() {
        this.add(this.information, BorderLayout.NORTH);
    }

    private void createBoard() {
        this.add(this.board, BorderLayout.CENTER);
    }

    private void createMenu() {
        this.createContents();
        this.setJMenuBar(this.mainMenu);
    }

    // REQUESTS 

    public Game getModel() {
        return this.model;
    }

    // ENTERED POINT

    public static void main(String[] args) {
        JFrame frame = new DisplayWindow();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }
}