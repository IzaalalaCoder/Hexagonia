package hex.game;

import hex.game.player.PlayerName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    @Test
    void shouldCreateAction() {
        // GIVEN

        Action a;

        // WHEN

        a = new Action(1, 7, PlayerName.MAX);

        // THEN

        assertTrue(a.getAbscissa() == 1
                && a.getOrdinate() == 7);
    }
}