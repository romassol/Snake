package snake;

public class Level {
    public FieldObject[][] field;
    public Snake snake;
    public AppleGenerator appleGenerator;

    public Level(
            int width,
            int height,
            int applesCount,
            int snakeX,
            int snakeY,
            Vector snakeDirection) {
        field = new FieldObject[height][width];
        appleGenerator = new AppleGenerator(applesCount);
        snake = new Snake(snakeX, snakeY, snakeDirection);
        field[snakeY][snakeX] = snake.head;
    }

    public Level(FieldReader reader, int applesCount) {
        field = reader.getField();
        snake = reader.getSnake();
        appleGenerator = new AppleGenerator(applesCount);
    }

    public FieldObject moveSnakeAndReturnOldCell(Vector snakeDirection) {
        SnakePart snakePartNow = snake.head.child;
        Vector parentDirection = snake.head.direction;
        Empty emptyObj = new Empty();

        while (snakePartNow != null) {
            int x = snakePartNow.getX() + parentDirection.DELTA_X;
            int y = snakePartNow.getY() + parentDirection.DELTA_Y;

            Vector tmp = snakePartNow.direction.clone();
            field[snakePartNow.getY()][snakePartNow.getX()] = emptyObj;
            snakePartNow.direction = parentDirection;
            snakePartNow.setPosition(x, y);
            field[y][x] = snakePartNow;

            parentDirection = tmp;
            snakePartNow = snakePartNow.child;
        }

        return moveSnakeHeadAndReturnOldCell(snakeDirection);
    }

    private FieldObject moveSnakeHeadAndReturnOldCell(Vector snakeDirection) {
        Vector direction;

        if (snakeDirection == null ||
                snake.head.direction.isOpposite(snakeDirection))
            direction = snake.head.direction;
        else
            direction = snakeDirection;

        int x = snake.head.getX() + direction.DELTA_X;
        int y = snake.head.getY() + direction.DELTA_Y;

        FieldObject oldCell = field[y][x];
        if (snake.head.child == null)
            field[snake.head.getY()][snake.head.getX()] = new Empty();
        snake.head.setPosition(x, y);
        snake.head.direction = direction;
        field[y][x] = snake.head;

        return oldCell;
    }

    public void addSnakePart() {
        SnakePart tail = snake.addPartAndReturnTail();
        field[tail.getY()][tail.getX()] = tail;
    }

    public boolean isOver(){
        return appleGenerator.getApplesCount() == 0;
    }
}
