package hex.board.cell;

import java.awt.*;

public enum Direction {

    // VALEURS PREDEFINIES

    LEFT("Left") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x, y - 1);
        }
    },
    RIGHT("Right") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x, y + 1);
        }
    },
    TOP("Top") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x - 1, y);
        }
    },
    BOTTOM("Bottom") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x + 1, y);
        }
    },
    TOP_LEFT("Top Left") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x - 1, y);
        }
    },
    TOP_RIGHT("Top Right") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x - 1, y + 1);
        }
    },
    BOTTOM_LEFT("Bottom Left") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x + 1, y - 1);
        }
    },
    BOTTOM_RIGHT("Bottom Right") {
        @Override
        public Point getNewCoordinates(int x, int y) {
            return new Point(x + 1, y);
        }
    };

    // CONSTRUCTEUR

    Direction(String name) {
        this.name = name;
    }

    // REQUESTS

    public abstract Point getNewCoordinates(int x, int y);

    public String getName() {
        return name;
    }


    // ATTRIBUTS

    private final String name;
}