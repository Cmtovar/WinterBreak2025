import org.example.engine.Vector3Axis;
import org.example.game.Player;
import org.example.game.World;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    // todo: consider add @BeforeEach to help setup

    @Test
    public void playerMovesForwardTest() {
        World world = new World();
        Player player = new Player(new Vector3Axis(0, 1, 0), world);

        player.moveForward(1.0);

        assertEquals(1.0, player.getPosition().z, 0.01);
    }

    @Test
    public void playerCannotLeaveGrassPathTest() {
        World world = new World();
        Player player = new Player(new Vector3Axis(0, 1, 0), world);

        // try to move far beyond the end (z=10 is max grass)
        for (int i = 0; i < 100; i++) {
            player.moveForward(1.0);
        }

        // should be stopped at or before z=10
        assertTrue(player.getPosition().z <= 10.5);
    }
}
