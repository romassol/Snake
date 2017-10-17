package snake;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class FieldReaderTest {
    @Test
    public void getSnakeFromFieldReader() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IOException, IllegalAccessException {
        FieldReader r = new FieldReader("level2.txt");
        Snake snake = r.getSnake();
        FieldObject[][] objects = r.getObjects();
        assertEquals(9, snake.head.getX());
        assertEquals(2, snake.head.getY());
        SnakePart next = snake.head;
        assertEquals(9, next.child.getX());
        assertEquals(3, next.child.getY());
        next = next.child;
        assertEquals(10, next.child.getX());
        assertEquals(3, next.child.getY());
        next = next.child;
        assertEquals(10, next.child.getX());
        assertEquals(2, next.child.getY());
        next = next.child;
        assertEquals(10, next.child.getX());
        assertEquals(1, next.child.getY());
    }
}
