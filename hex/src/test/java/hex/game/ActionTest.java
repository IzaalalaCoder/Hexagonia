package hex.game;

import hex.game.player.computer.Computer;
import hex.game.player.computer.Level;
import hex.platform.view.info.PlayerName;
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