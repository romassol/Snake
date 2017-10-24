package snake;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class SnakeTest {

    private void fillField(Level level){
        level.field[0][0] = new Empty();
        level.field[0][1] = new Empty();
        level.field[0][2] = new Empty();
        level.field[1][0] = new Apple();
        level.field[1][1] = new Wall();
        level.field[1][2] = new Empty();
        level.field[2][0] = new Wall();
        level.field[2][1] = new Empty();
    }

    @Test
    public void snake_addPartAndReturnTail_newSnake() {
        Snake snake = new Snake(2, 0, Direction.RIGHT);
        SnakePart head = snake.getHead();

        SnakePart tail = snake.addPartAndReturnTail();

        assertEquals(head, snake.getHead());
        assertEquals(tail, snake.getTail());
        assertEquals(1, tail.getX());
        assertEquals(0, tail.getY());
        assertEquals(Direction.RIGHT, tail.getDirection());
        assertEquals(tail, snake.getHead().getChild());
        assertEquals(head, snake.getTail().getParent());
    }

    @Test
    public void snake_addPartAndReturnTail_notEmptySnake() {
        Snake snake = new Snake(2, 2, Direction.BOTTOM);
        snake.addPartAndReturnTail();
        SnakePart tail = snake.addPartAndReturnTail();

        assertEquals(snake.getHead().getChild().getChild(), tail);
        assertEquals(snake.getHead().getChild(), tail.getParent());
        assertEquals(null, tail.getChild());
    }

    @Test
    public void level_addSnakePart() {
        Level level = new Level(
                3, 4, 3, 1, 1, Direction.TOP);
        level.addSnakePart();
        level.addSnakePart();

        assertEquals(level.field[2][1], level.snake.getHead().getChild());
        assertEquals(level.field[3][1], level.snake.getTail());
    }
    
    @Test
    public void someElementsSnakeMoving() {
        Level level = new Level(
                3 ,3,1, 2, 2, Direction.RIGHT);
        fillField(level);
        level.addSnakePart();

        level.moveSnakeAndReturnOldCell(Direction.TOP);
        assertEquals(2, level.snake.getHead().getX());
        assertEquals(1, level.snake.getHead().getY());
        assertEquals(2, level.snake.getTail().getX());
        assertEquals(2, level.snake.getTail().getY());
        assertTrue(level.field[2][1] instanceof Empty);
    }

    @Test
    public void oneElementSnakeMoving() {
        Level level = new Level(
                3 ,3,1, 2, 2, Direction.LEFT);
        fillField(level);

        level.moveSnakeAndReturnOldCell(null);
        assertEquals(1, level.snake.getHead().getX());
        assertEquals(2, level.snake.getHead().getY());
    }

    @Test
    public void snakeMovingInCircle() {
        Level level = new Level(
                4 ,5,1, 1, 1, Direction.TOP);

        level.addSnakePart();
        level.snake.getTail().setDirection(Direction.LEFT);
        level.addSnakePart();
        level.snake.getTail().setDirection(Direction.BOTTOM);
        level.addSnakePart();
        SnakePart head = level.snake.getHead();

        IFieldObject oldCell = level.moveSnakeAndReturnOldCell(Direction.RIGHT);
        assertTrue(oldCell instanceof Empty);
        assertEquals(head, level.field[1][2]);
    }

    @Test
    public void goToTheNextLevel() throws Exception {
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