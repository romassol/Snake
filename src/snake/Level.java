package snake;

public class Level {
    public IFieldObject[][] field;
    public Snake snake;
    public AppleGenerator appleGenerator;

    public Level(
            int width,
            int height,
            int applesCount,
            int snakeX,
            int snakeY,
            Vector snakeDirection) {
        field = new IFieldObject[height][width];
        appleGenerator = new AppleGenerator(applesCount);
        snake = new Snake(snakeX, snakeY, snakeDirection);
        field[snakeY][snakeX] = snake.getHead();
    }

    public Level(FieldReader reader, int applesCount) {
        field = reader.getField();
        snake = reader.getSnake();
        appleGenerator = new AppleGenerator(applesCount);
    }

    public IFieldObject moveSnakeAndReturnOldCell(Vector snakeDirection) {
        SnakePart snakePartNow = snake.getHead().getChild();
        Vector parentDirection = snake.getHead().getDirection();
        Empty emptyObj = new Empty();

        while (snakePartNow != null) {
            Vector nextPosition = getCoordinates(
                    snakePartNow.getPosition(),
                    parentDirection);

            Vector tmp = snakePartNow.getDirection().clone();
            field[snakePartNow.getY()][snakePartNow.getX()] = emptyObj;
            snakePartNow.setDirection(parentDirection);
            snakePartNow.setPosition(nextPosition);
            field[nextPosition.Y][nextPosition.X] = snakePartNow;

            parentDirection = tmp;
            snakePartNow = snakePartNow.getChild();
        }

        return moveSnakeHeadAndReturnOldCell(snakeDirection);
    }

    private IFieldObject moveSnakeHeadAndReturnOldCell(Vector snakeDirection) {
        Vector direction;

        if (snakeDirection == null ||
                snake.getHead().getDirection().isOpposite(snakeDirection))
            direction = snake.getHead().getDirection();
        else
            direction = snakeDirection;

        Vector nextPosition = getCoordinates(
                snake.getHead().getPosition(),
                direction);

        IFieldObject oldCell = field[nextPosition.Y][nextPosition.X];
        if (snake.getHead().getChild() == null)
            field[snake.getHead().getY()][snake.getHead().getX()] = new Empty();
        snake.getHead().setPosition(nextPosition);
        snake.getHead().setDirection(direction);
        field[nextPosition.Y][nextPosition.X] = snake.getHead();

        return oldCell;
    }

    public void addSnakePart() {
        SnakePart tail = snake.addPartAndReturnTail();
        field[tail.getY()][tail.getX()] = tail;
    }

    public boolean isOver(){
        return appleGenerator.getApplesCount() == 0;
    }

    private Vector getCoordinates(Vector position, Vector direction) {
        Vector sumVector = position.sum(direction);
        return new Vector(
                (sumVector.X + field[0].length) % field[0].length,
                (sumVector.Y + field.length) % field.length
        );
    }
}
