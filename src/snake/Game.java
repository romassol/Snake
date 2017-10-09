package snake;

public class Game {
    public Level level;
    public Vector playerDirection;
    public boolean isGameOver;

    public void changeGameState() throws Exception {
        FieldObject oldCell = level.moveSnakeAndReturnOldCell(playerDirection);
        oldCell.intersectWithSnake(this);

        if (level.appleGenerator.isNeedToAdd(oldCell))
            level.appleGenerator.generate(level);
    }

    public Game(Level level, Vector playerDirection) {
        this.level = level;
        this.playerDirection = playerDirection;
        isGameOver = false;
    }


}
