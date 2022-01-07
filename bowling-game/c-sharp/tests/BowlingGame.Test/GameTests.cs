using Xunit;

namespace BowlingGame.Test;

public class GameTests
{
    [Fact]
    public void Test1()
    {
        var game = new Game();

        Assert.Equal(0, game.Score());
    }
}