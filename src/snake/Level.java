package snake;

public class Level {
    public FieldObject[][] objects;
    public Snake snake;
    public AppleGenerator appleGenerator;

    public Level(Integer width, Integer height, Integer applesCount) {
        objects = new FieldObject[width][height];
        appleGenerator = new AppleGenerator(applesCount);
    }

    public FieldObject moveSnakeAndReturnOldCell(Vector snakeDirection) {
        FieldObject oldCell = null;
        boolean isNeedToSaveOldCell = true;
        Vector snakePartToGo = snakeDirection;
        SnakePart snakePartNow = snake.head;
        while (snakePartNow != null) {
            Integer x = snakePartNow.x + snakePartToGo.DELTA_X;
            Integer y = snakePartNow.y + snakePartToGo.DELTA_Y;

            if (isNeedToSaveOldCell) {
                oldCell = objects[x][y];
                isNeedToSaveOldCell = false;
            }

            objects[x][y] = snakePartNow;
            snakePartNow.x = x;
            snakePartNow.y = y;

            Vector futurePartNowDirection = snakePartToGo.copy();
            snakePartToGo = snakePartNow.direction;
            snakePartNow.direction = futurePartNowDirection;

            snakePartNow = snakePartNow.child;
        }

        return oldCell;
    }

    public void addSnakePart() {
        SnakePart tail = snake.addPartAndReturnTail();
        objects[tail.x][tail.y] = tail;
    }
}
