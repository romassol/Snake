package snake;

public class Level {
    public FieldObject[][] field;
    public Snake snake;
    public AppleGenerator appleGenerator;

    public Level(
            int width,
            int height,
            int applesCount,
            Vector snakePosition,
            Vector snakeDirection) {
        field = new FieldObject[height][width];
        appleGenerator = new AppleGenerator(applesCount);
        snake = new Snake(snakePosition.X, snakePosition.Y, snakeDirection);
        field[snakePosition.Y][snakePosition.X] = snake.getHead();
    }

    public Level(FieldReader reader, int applesCount) {
        field = reader.getField();
        snake = reader.getSnake();
        appleGenerator = new AppleGenerator(applesCount);
    }

    public FieldObject moveSnakeAndReturnOldCell(Vector snakeDirection) {
        SnakePart currentSnakePart = snake.getHead().getChild();
        Vector parentDirection = snake.getHead().getDirection();
        Empty emptyObj = new Empty();

        while (currentSnakePart != null) {
            Vector nextPosition = getCoordinates(
                    currentSnakePart.getPosition(),
                    parentDirection);

            Vector tmp = currentSnakePart.getDirection();
            field[currentSnakePart.getY()][currentSnakePart.getX()] = emptyObj;
            currentSnakePart.setDirection(parentDirection);
            currentSnakePart.setPosition(nextPosition);
            field[nextPosition.Y][nextPosition.X] = currentSnakePart;

            parentDirection = tmp;
            currentSnakePart = currentSnakePart.getChild();
        }

        return moveSnakeHeadAndReturnOldCell(snakeDirection);
    }

    private FieldObject moveSnakeHeadAndReturnOldCell(Vector snakeDirection) {
        Vector direction;

        if (snakeDirection == null ||
                snake.getHead().getDirection().isOpposite(snakeDirection)) {
            direction = snake.getHead().getDirection();
        }
        else {
            direction = snakeDirection;
        }

        Vector nextPosition = getCoordinates(
                snake.getHead().getPosition(),
                direction);

        FieldObject oldCell = field[nextPosition.Y][nextPosition.X];
        if (snake.getHead().getChild() == null) {
            field[snake.getHead().getY()][snake.getHead().getX()] = new Empty();
        }
        snake.getHead().setPosition(nextPosition);
        snake.getHead().setDirection(direction);
        field[nextPosition.Y][nextPosition.X] = snake.getHead();

        return oldCell;
    }

    public void addSnakePart() {
        SnakePart tail = snake.addPartAndReturnTail();
        field[tail.getY()][tail.getX()] = tail;
    }

    public boolean isOver() {
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
