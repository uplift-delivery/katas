public class BowlingGame {

    private int total = 0;

    public void roll(int pins) {
        total += pins;
    }

    public int score() {
        return total;
    }
}
