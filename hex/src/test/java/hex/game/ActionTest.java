package hex.game;

import hex.model.board.cell.Cell;
import hex.model.board.cell.Shape;
import hex.model.game.Action;
import hex.model.game.player.computer.Computer;
import hex.model.game.player.computer.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    @Test
    void shouldCreateAction() {
        // GIVEN

        Computer compute = new Computer(1, Level.EASY);
        Action a;
        Cell c = new Cell(Shape.HEXAGONAL, 1, 1, 7);

        // WHEN

        a = new Action(c, compute);

        // THEN

        assertTrue(a.getCell().getAbscissa() == 1
                && a.getCell().getOrdinate() == 7);
        assertEquals(compute, a.getPlayer());
    }
}