package snake;

import org.junit.Test;

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
    public void snakeMoving() throws Exception {
        Level level = new Level(
                3 ,3,1, 2, 2, Direction.LEFT);
        Game game = new Game(level);
        game.changeGameState();
        assertEquals(game.getLevel().snake.head.getX(), 1);
        assertEquals(game.getLevel().snake.head.getY(), 2);
        assertEquals(game.getLevel().snake.head.child, null);
        assertEquals(game.getLevel().snake.head.parent, null);
    }

    @Test
    public void addPartAndReturnTail_newSnake() {
        Snake snake = new Snake(2, 0, Direction.RIGHT);
        SnakePart head = snake.head;

        SnakePart newTail = snake.addPartAndReturnTail();

        assertEquals(head, snake.head);
        assertEquals(newTail, snake.tail);
        assertEquals(1, newTail.getX());
        assertEquals(0, newTail.getY());
        assertEquals(Direction.RIGHT, newTail.direction);
        assertEquals(newTail, snake.head.child);
        assertEquals(head, snake.tail.parent);
    }

    public void addPartAndReturnTail_notEmptySnake() {

    }
}