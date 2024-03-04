package hex.game.theoric.structure;

import hex.board.cell.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArborescenceTest {

    @Test
    public void shouldCreateWithoutBoard() {

        // Given and When and Then

        assertThrows(
                IllegalArgumentException.class,
                () ->
                       new Arborescence(null),
                ""
        );
    }
}