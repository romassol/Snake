package snake;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FieldReaderTest {
    @Test
    public void readLevelFromFile_snakeWithSomeSnakeParts() throws Exception {
        FieldReader r = new FieldReader("level4.txt");
        Vector[] expected = new Vector[5];
        expected[0] = new Vector(21, 4);
        expected[1] = new Vector(21, 5);
        expected[2] = new Vector(22, 5);
        expected[3] = new Vector(22, 4);
        expected[4] = new Vector(22, 3);
        Snake snake = r.getSnake();
        SnakePart next = snake.getHead();

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].X, next.getX());
            assertEquals(expected[i].Y, next.getY());
            next = next.getChild();
        }
        assertTrue(next == null);
    }
    @Test
    public void setDirection() throws Exception {
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
