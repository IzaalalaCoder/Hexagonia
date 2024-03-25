package hex.board.cell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void shouldCreateValidCell() {
        // GIVEN

        Cell c;
        Shape s = Shape.SQUARE;

        // WHEN

        c = new Cell(s, 0);

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
                        new Cell(null, 0),
                        "Invalid argument"
        );
    }

    @Test
    public void shouldSetCellOnDirWhenDirectionIsNull() {

        // GIVEN

        Cell c = new Cell(Shape.TRIANGLE_B, 0);

        // THEN

        assertThrows(
                IllegalArgumentException.class,
                () ->
                        // WHEN

                        c.setCellOnDirection(null, c),
                "Invalid argument"
        );
    }

    @Test
    public void shouldSetCellOnDirWithCorrect() {
        // GIVEN
        Shape s = Shape.SQUARE;
        Cell c = new Cell(s, 0);
        Cell c1 = new Cell(s, 0);

        // WHEN

        c.setCellOnDirection(Direction.BOTTOM, c1);

        // THEN

        assertSame(c.getCellOnDir(Direction.BOTTOM), c1);
    }

    @Test
    public void shouldSetCellOnDirWithNotCorrectCell() {
        // GIVEN

        Cell c = new Cell(Shape.SQUARE, 0);

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

        Cell c = new Cell(Shape.SQUARE, 0);

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

        Cell c = new Cell(Shape.SQUARE, 0);

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

        Cell c = new Cell(Shape.SQUARE, 0);

        // WHEN

        c.setState(State.PLAYER);

        // THEN

        assertSame(c.getState(), State.PLAYER);
    }

    @Test
    public void shouldSetInvalidState() {
        // GIVEN

        Cell c = new Cell(Shape.SQUARE, 0);

        // THEN

        assertThrows(
                IllegalArgumentException.class,
                () ->
                        // WHEN

                        c.setState(null),
                "Invalid argument"
        );
    }

    @Test
    public void shouldGetCellOnDirWhenIsNull() {
        // GIVEN

        Cell c = new Cell(Shape.TRIANGLE_B, 0);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.getCellOnDir(null),
                "Invalid argument"

        );
    }

    @Test
    public void shouldGetCellOnDirWhenIsInexistantDirection() {
        // GIVEN

        Cell c = new Cell(Shape.TRIANGLE_B, 0);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.getCellOnDir(Direction.TOP),
                "Invalid argument"

        );
    }

    @Test
    public void shouldCopyCell() {
        // GIVEN

        Cell c = new Cell(Shape.TRIANGLE_T, 0);

        // WHEN

        Cell newC = Cell.copyCell(c);

        // THEN

        assertEquals(c.getState(), newC.getState());
        assertEquals(c.getShape(), newC.getShape());
        assertEquals(c.getDirections(), newC.getDirections());
    }

}