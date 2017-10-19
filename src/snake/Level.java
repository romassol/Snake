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
        field[snakeY][snakeX] = snake.getHead();
    }

    public Level(FieldReader reader, int applesCount) {
        field = reader.getField();
        snake = reader.getSnake();
        appleGenerator = new AppleGenerator(applesCount);
    }

    public FieldObject moveSnakeAndReturnOldCell(Vector snakeDirection) {
        SnakePart snakePartNow = snake.getHead().getChild();
        Vector parentDirection = snake.getHead().getDirection();
        Empty emptyObj = new Empty();

        while (snakePartNow != null) {
            int x = snakePartNow.getX() + parentDirection.DELTA_X;
            int y = snakePartNow.getY() + parentDirection.DELTA_Y;

            Vector tmp = snakePartNow.getDirection().clone();
            field[snakePartNow.getY()][snakePartNow.getX()] = emptyObj;
            snakePartNow.setDirection(parentDirection);
            snakePartNow.setPosition(x, y);
            field[y][x] = snakePartNow;

            parentDirection = tmp;
            snakePartNow = snakePartNow.getChild();
        }

        return moveSnakeHeadAndReturnOldCell(snakeDirection);
    }

    private FieldObject moveSnakeHeadAndReturnOldCell(Vector snakeDirection) {
        Vector direction;

        if (snakeDirection == null ||
                snake.getHead().getDirection().isOpposite(snakeDirection))
            direction = snake.getHead().getDirection();
        else
            direction = snakeDirection;

        int x = snake.getHead().getX() + direction.DELTA_X;
        int y = snake.getHead().getY() + direction.DELTA_Y;

        FieldObject oldCell = field[y][x];
        if (snake.getHead().getChild() == null)
            field[snake.getHead().getY()][snake.getHead().getX()] = new Empty();
        snake.getHead().setPosition(x, y);
        snake.getHead().setDirection(direction);
        field[y][x] = snake.getHead();

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
