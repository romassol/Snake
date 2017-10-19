package snake;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FieldReaderTest {
    @Test
    public void snakePosition() throws InvocationTargetException,
            NoSuchMethodException, InstantiationException,
            IOException, IllegalAccessException {
        FieldReader r = new FieldReader("level2.txt");
        Snake snake = r.getSnake();
        assertEquals(9, snake.getHead().getX());
        assertEquals(2, snake.getHead().getY());
        SnakePart next = snake.getHead();
        assertEquals(9, next.getChild().getX());
        assertEquals(3, next.getChild().getY());
        next = next.getChild();
        assertEquals(10, next.getChild().getX());
        assertEquals(3, next.getChild().getY());
        next = next.getChild();
        assertEquals(10, next.getChild().getX());
        assertEquals(2, next.getChild().getY());
        next = next.getChild();
        assertEquals(10, next.getChild().getX());
        assertEquals(1, next.getChild().getY());
    }
    @Test
    public void setDirection() throws InvocationTargetException, 
            NoSuchMethodException, InstantiationException,
            IOException, IllegalAccessException {
        FieldReader r = new FieldReader("level4.txt");
        Snake snake = r.getSnake();
        assertTrue(Direction.TOP.isEqualWithOther(
                snake.getHead().getDirection()));
        SnakePart next = snake.getHead();
        assertTrue(Direction.LEFT.isEqualWithOther(
                next.getChild().getDirection()));
        next = next.getChild();
        assertTrue(Direction.BOTTOM.isEqualWithOther(
                next.getChild().getDirection()));
        next = next.getChild();
        assertTrue(Direction.BOTTOM.isEqualWithOther(
                next.getChild().getDirection()));
        next = next.getChild();
        assertTrue(Direction.BOTTOM.isEqualWithOther(
                next.getChild().getDirection()));
    }
}
