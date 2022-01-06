import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {
    @Test
    public void allGutterBalls() {
        var game = new BowlingGame();

        int pins = 0;
        for (int i = 0; i < 20; i++) {
            game.roll(pins);
        }

        assertEquals(pins, game.score());
    }

    @Test
    public void allSinglePin() {
        var game = new BowlingGame();

        int pins = 1;
        for (int i = 0; i < 20; i++) {
            game.roll(pins);
        }

        assertEquals(20, game.score());
    }

    @Test
    public void allOpenFrames() {
        var game = new BowlingGame();

        game.roll(3);
        game.roll(6);

        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }

        assertEquals(9, game.score());
    }

    @Test
    public void () {
        var game = new BowlingGame();

    }
}
