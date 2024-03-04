package hex.board.cell;

public enum Direction {

    // VALEURS PREDEFINIES

    LEFT("L"),
    RIGHT("R"),
    TOP("T"),
    BOTTOM("B"),
    TOP_LEFT("TL"),
    TOP_RIGHT("TR"),
    BOTTOM_LEFT("BL"),
    BOTTOM_RIGHT("BR");

    // CONSTRUCTEUR

    Direction(String name) {
        this.name = name;
    }

    // ATTRIBUTS

    private String name;
}
