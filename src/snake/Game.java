package snake;

public class Game {
    private Level level;
    private Vector playerDirection;
    public boolean isGameOver;

    public void changeGameState() throws Exception {
        FieldObject oldCell = level.moveSnakeAndReturnOldCell(playerDirection);
        oldCell.intersectWithSnake(this);

        if (level.appleGenerator.isNeedToAdd(oldCell))
            level.appleGenerator.generate(level);
    }

    public Game(Level level) {
        this.level = level;
        isGameOver = false;
    }

    public void setPlayerDirection(Vector playerDirection) {
        this.playerDirection = playerDirection;
    }

    public Level getLevel() {
        return level;
    }
}
