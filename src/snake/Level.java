package snake;

public class Level {
    public FieldObject[][] objects;
    public Snake snake;
    public AppleGenerator appleGenerator;

    public Level(
            int width,
            int height,
            int applesCount,
            int snakeX,
            int snakeY,
            Vector snakeDirection) {
        objects = new FieldObject[width][height];
        appleGenerator = new AppleGenerator(applesCount);
        snake = new Snake(snakeX, snakeY, snakeDirection);
        objects[snakeX][snakeY] = snake.head;
    }

    public Level(FieldReader reader, int applesCount) {
        objects = reader.getObjects();
        snake = new Snake(reader.getSnakePart());
        appleGenerator = new AppleGenerator(applesCount);
    }

    public FieldObject moveSnakeAndReturnOldCell(Vector snakeDirection) {
        FieldObject oldCell = null;
        boolean isNeedToSaveOldCell = true;
        Vector snakePartToGo = snakeDirection;
        SnakePart snakePartNow = snake.head;
        int tailX = snake.tail.getX();
        int tailY = snake.tail.getY();
        while (snakePartNow != null) {
            int x = snakePartNow.getX() + snakePartToGo.DELTA_X;
            int y = snakePartNow.getY() + snakePartToGo.DELTA_Y;

            if (isNeedToSaveOldCell) {
                oldCell = objects[x][y];
                isNeedToSaveOldCell = false;
            }

            objects[x][y] = snakePartNow;
            snakePartNow.setX(x);
            snakePartNow.setY(y);

            Vector futurePartNowDirection = snakePartToGo.copy();
            snakePartToGo = snakePartNow.direction;
            snakePartNow.direction = futurePartNowDirection;
            snakePartNow = snakePartNow.child;
        }
        objects[tailY][tailX] = new Empty();

        return oldCell;
    }

    public void addSnakePart() {
        SnakePart tail = snake.addPartAndReturnTail();
        objects[tail.getX()][tail.getY()] = tail;
    }
}
