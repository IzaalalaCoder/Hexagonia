package hex.game;

import hex.model.game.Action;
import hex.model.game.player.computer.Computer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    @Test
    void shouldCreateAction() {
        // GIVEN

        Computer compute = new Computer(1);
        Action a;

        // WHEN

        a = new Action(1, 7, compute);

        // THEN

        assertTrue(a.getAbscissa() == 1
                && a.getOrdinate() == 7);
        assertEquals(compute, a.getPlayer());
    }
}