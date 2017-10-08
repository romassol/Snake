package snake;

public class Game {
    public Field field;
    private AppleGenerator appleGenerator;
    public Vector playerDirection;
    public boolean isGameOver;

    public void tick() throws Exception {
        FieldObject oldCell = field.moveSnakeAndReturnOldCell(playerDirection);
        oldCell.intersectWithSnake(this);

        if (appleGenerator.isNeedToAdd(oldCell))
            appleGenerator.generate(field);
    }
}
