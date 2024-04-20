package hex.model.board.cell;

import java.awt.*;

public enum Direction {

    // VALUES

    LEFT() {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x, y - 1);
        }
    },
    RIGHT() {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x, y + 1);
        }
    },
    TOP_LEFT() {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x - 1, y);
        }
    },
    TOP_RIGHT() {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x - 1, y + 1);
        }
    },
    BOTTOM_LEFT() {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x + 1, y - 1);
        }
    },
    BOTTOM_RIGHT() {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x + 1, y);
        }
    };

    // REQUESTS

    public abstract Point getNewCoordinates(int x, int y);

}