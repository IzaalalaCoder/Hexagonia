package hex.board.cell;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void shouldCreateValidCell() {
        // GIVEN

        Cell c;
        Shape s = Shape.SQUARE;

        // WHEN

        c = new Cell(s);

        // THEN

        assertTrue(c.getShape() == s
                && c.getState() == State.EMPTY
                && c.getDirections().size() == s.getNbSides()
        );
    }

    @Test
    public void shouldCreateInvalidCell() {
        // THEN

        assertThrows(
                IllegalArgumentException.class,
                () ->
                        // WHEN
                        new Cell(null),
                        "Invalid argument"
        );
    }

    @Test
    public void shouldSetCellOnDirWithCorrect() {
        // GIVEN
        Shape s = Shape.SQUARE;
        Cell c = new Cell(s);
        Cell c1 = new Cell(s);

        // WHEN

        c.setCellOnDirection(Direction.BOTTOM, c1);

        // THEN

        assertSame(c.getCellOnDir(Direction.BOTTOM), c1);
    }

    @Test
    public void shouldSetCellOnDirWithNotCorrectCell() {
        // GIVEN

        Cell c = new Cell(Shape.SQUARE);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.setCellOnDirection(Direction.BOTTOM, null),
                "Invalid argument"

        );
    }

    @Test
    public void shouldSetCellOnDirWithNotCorrectDir() {
        // GIVEN

        Cell c = new Cell(Shape.SQUARE);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.setCellOnDirection(null, c),
                "Invalid argument"

        );
    }

    @Test
    public void shouldSetCellOnDirWithSameCell() {
        // GIVEN

        Cell c = new Cell(Shape.SQUARE);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.setCellOnDirection(Direction.BOTTOM, c),
                "Don't cell correct"

        );
    }

    @Test
    public void shouldSetValidState() {
        // GIVEN

        Cell c = new Cell(Shape.SQUARE);

        // WHEN

        c.setState(State.PLAYER);

        // THEN

        assertSame(c.getState(), State.PLAYER);
    }

    @Test
    public void shouldSetInvalidState() {
        // GIVEN

        Cell c = new Cell(Shape.SQUARE);

        // THEN

        assertThrows(
                IllegalArgumentException.class,
                () ->
                        // WHEN

                        c.setState(null),
                "Invalid argument"
        );
    }

}