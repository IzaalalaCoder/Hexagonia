package hex.board.cell;

import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void shouldCreateValidCell() {
        // GIVEN

        Cell c;

        // WHEN

        c = new Cell(0, 0, 0);

        // THEN

        assertTrue(c.getState() == State.EMPTY);
        assertSame(6, c.getDirections().size());
    }

    @Test
    public void shouldSetCellOnDirWhenDirectionIsNull() {

        // GIVEN

        Cell c = new Cell(0, 0, 0);

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
        Cell c = new Cell(0, 0, 0);
        Cell c1 = new Cell(0, 0, 0);

        // WHEN

        c.setCellOnDirection(Direction.LEFT, c1);

        // THEN

        assertSame(c.getCellOnDir(Direction.LEFT), c1);
    }

    @Test
    public void shouldSetCellOnDirWithNotCorrectCell() {
        // GIVEN

        Cell c = new Cell(0, 0, 0);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.setCellOnDirection(Direction.TOP_LEFT, null),
                "Invalid argument"

        );
    }

    @Test
    public void shouldSetCellOnDirWithNotCorrectDir() {
        // GIVEN

        Cell c = new Cell(0, 0, 0);

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

        Cell c = new Cell(0, 0, 0);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.setCellOnDirection(Direction.TOP_LEFT, c),
                "Don't cell correct"

        );
    }

    @Test
    public void shouldGetCellOnDirWhenIsNull() {
        // GIVEN

        Cell c = new Cell(0, 0, 0);

        // THEN

        assertThrows(IllegalArgumentException.class,
                () ->
                        // WHEN
                        c.getCellOnDir(null),
                "Invalid argument"

        );
    }

    @Test
    public void shouldCopyCell() {
        // GIVEN

        Cell c = new Cell( 0, 0, 0);

        // WHEN

        Cell newC = c.copyCell();

        // THEN

        assertEquals(c.getState(), newC.getState());
        assertEquals(c.getDirections(), newC.getDirections());
    }

}