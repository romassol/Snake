package snake;

public class Level {
    public FieldObject[][] objects;
    public Snake snake;
    public AppleGenerator appleGenerator;

    public Level(int width, int height, int applesCount) {
        objects = new FieldObject[width][height];
        appleGenerator = new AppleGenerator(applesCount);
    }
    public Level(FieldObject[][] field, int applesCount) {
        objects = field;
        appleGenerator = new AppleGenerator(applesCount);
        createSnake();
    }

    private void createSnake(){
        for(int i = 0; i < objects.length; i++){
            for(int j = 0; j < objects[i].length; j++){
                if (objects[i][j] instanceof SnakePart){
                    SnakePart part = (SnakePart) objects[i][j];
                    snake = new Snake(j, i, part.direction);
                }
            }
        }
    }

    public FieldObject moveSnakeAndReturnOldCell(Vector snakeDirection) {
        FieldObject oldCell = null;
        boolean isNeedToSaveOldCell = true;
        Vector snakePartToGo = snakeDirection;
        SnakePart snakePartNow = snake.head;
        int tailX = snake.tail.x;
        int tailY = snake.tail.y;
        while (snakePartNow != null) {
            int x = snakePartNow.x + snakePartToGo.DELTA_X;
            int y = snakePartNow.y + snakePartToGo.DELTA_Y;

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
        objects[tailY][tailX] = new Empty(tailX,tailY);

        return oldCell;
    }

    public void addSnakePart() {
        SnakePart tail = snake.addPartAndReturnTail();
        objects[tail.x][tail.y] = tail;
    }
}
