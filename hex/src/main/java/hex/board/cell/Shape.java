package hex.board.cell;

import java.util.ArrayList;
import java.util.List;

public enum Shape {
    // Valeurs prédéfinies

    SQUARE(4) {
        @Override
        public List<Direction> getDirections() {
            List<Direction> directions = new ArrayList<Direction>();
            {
                Shape.getCommonsDirection(directions);
                directions.add(Direction.TOP);
                directions.add(Direction.BOTTOM);
            }
            return directions;
        }
    },
    HEXAGONAL(6) {
        @Override
        public List<Direction> getDirections() {
            List<Direction> directions = new ArrayList<Direction>();
            {
                Shape.getCommonsDirection(directions);
                directions.add(Direction.TOP_RIGHT);
                directions.add(Direction.TOP_LEFT);
                directions.add(Direction.BOTTOM_RIGHT);
                directions.add(Direction.BOTTOM_LEFT);
            }
            return directions;
        }
    },
    TRIANGLE_T(3) {
        @Override
        public List<Direction> getDirections() {
            List<Direction> directions = new ArrayList<Direction>();
            {
                Shape.getCommonsDirection(directions);
                directions.add(Direction.TOP);
            }
            return directions;
        }
    },
    TRIANGLE_B(3) {
        @Override
        public List<Direction> getDirections() {
            List<Direction> directions = new ArrayList<Direction>();
            {
                Shape.getCommonsDirection(directions);
                directions.add(Direction.BOTTOM);
            }
            return directions;
        }
    };

    // OUTILS

    private static void getCommonsDirection(List<Direction> directions) {
        {
            directions.add(Direction.LEFT);
            directions.add(Direction.RIGHT);
        }
    }

    // Attributs

    private final int nbSides;
    private final List<Direction> directions;

    // Requêtes

    public int getNbSides() {
        return this.nbSides;
    }
    public abstract List<Direction> getDirections();

    // Constructeurs
    Shape(int sides) {
        this.nbSides = sides;
        this.directions = this.getDirections();
    }
}