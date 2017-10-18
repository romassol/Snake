package snake;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class SnakeTest {

    private void fillField(Level level){
        level.objects[0][0] = new Empty();
        level.objects[0][1] = new Empty();
        level.objects[0][2] = new Empty();
        level.objects[1][0] = new Apple();
        level.objects[1][1] = new Wall();
        level.objects[1][2] = new Empty();
        level.objects[2][0] = new Wall();
        level.objects[2][1] = new Empty();
    }

    @Test
    public void snake_addPartAndReturnTail_newSnake() {
        Snake snake = new Snake(2, 0, Direction.RIGHT);
        SnakePart head = snake.head;

        SnakePart tail = snake.addPartAndReturnTail();

        assertEquals(head, snake.head);
        assertEquals(tail, snake.tail);
        assertEquals(1, tail.getX());
        assertEquals(0, tail.getY());
        assertEquals(Direction.RIGHT, tail.direction);
        assertEquals(tail, snake.head.child);
        assertEquals(head, snake.tail.parent);
    }

    @Test
    public void snake_addPartAndReturnTail_notEmptySnake() {
        Snake snake = new Snake(2, 2, Direction.BOTTOM);
        snake.addPartAndReturnTail();
        SnakePart tail = snake.addPartAndReturnTail();

        assertEquals(snake.head.child.child, tail);
        assertEquals(snake.head.child, tail.parent);
        assertEquals(null, tail.child);
    }

    @Test
    public void level_addSnakePart() {
        Level level = new Level(3, 4, 3, 1, 1, Direction.TOP);
        level.addSnakePart();
        level.addSnakePart();

        assertEquals(level.objects[1][2], level.snake.head.child);
        assertEquals(level.objects[1][3], level.snake.tail);
    }

    @Test
    public void someElementsSnakeMoving() {
        Level level = new Level(
                3 ,3,1, 2, 2, Direction.RIGHT);
        fillField(level);
        level.addSnakePart();

        level.moveSnakeAndReturnOldCell(Direction.TOP);
        assertEquals(2, level.snake.head.getX());
        assertEquals(1, level.snake.head.getY());
        assertEquals(2, level.snake.tail.getX());
        assertEquals(2, level.snake.tail.getY());
        assertTrue(level.objects[1][2] instanceof Empty);
    }

    @Test
    public void oneElementSnakeMoving() {
        Level level = new Level(
                3 ,3,1, 2, 2, Direction.LEFT);
        fillField(level);

        level.moveSnakeAndReturnOldCell(null);
        assertEquals(1, level.snake.head.getX());
        assertEquals(2, level.snake.head.getY());
    }

    @Test
    public void snakeMovingInCircle() {
        Level level = new Level(
                4 ,5,1, 1, 1, Direction.TOP);

        level.addSnakePart();
        level.snake.tail.direction = Direction.LEFT;
        level.addSnakePart();
        level.snake.tail.direction = Direction.BOTTOM;
        level.addSnakePart();
        SnakePart head = level.snake.head;

        FieldObject oldCell = level.moveSnakeAndReturnOldCell(Direction.RIGHT);
        assertTrue(oldCell instanceof Empty);
        assertEquals(head, level.objects[2][1]);
    }

    @Test
    public void goToTheNextLevel() throws MakeTurnException, InvocationTargetException, NoSuchMethodException, InstantiationException, IOException, IllegalAccessException {
        Level[] levels = new Level[2];
        levels[0] = new Level(new FieldReader("level5.txt"), 0);
        levels[1] = new Level(new FieldReader("level4.txt"), 1);
        Game game = new Game(levels);
        assertEquals(levels[0], game.getCurrentLevel());
        game.setPlayerDirection(Direction.RIGHT);
        game.makeTurn();
        System.out.println(game.getCurrentLevel().isOver());
        assertEquals(levels[1], game.getCurrentLevel());
    }
}