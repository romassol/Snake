package snake;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {

    private FieldObject[][] fillField(){
        FieldObject[][] field = new FieldObject[3][3];
        field[0][0] = new Empty(0,0);
        field[0][1] = new Empty(0,1);
        field[0][2] = new Empty(0,2);
        field[1][0] = new Apple(1,0);
        field[1][1] = new Wall(1,1);
        field[1][2] = new Empty(1,2);
        field[2][0] = new Wall(2,0);
        field[2][1] = new Empty(2,1);
        field[2][2] = new SnakePart(2,2, Direction.LEFT, null,null);
        return field;
    }
    private FieldObject[][] field = fillField();

    @Test
    public void SnakeMoving() throws Exception {
        Level level1 = new Level(field,1);
        Game game = new Game(level1, Direction.LEFT);
        game.changeGameState();
        assertEquals(game.level.snake.head.x, 1);
        assertEquals(game.level.snake.head.y, 2);
        assertEquals(game.level.snake.head.child, null);
        assertEquals(game.level.snake.head.parent, null);
        assertTrue(game.level.objects[2][2] instanceof Empty);
//        System.out.println(level1.snake);
//        System.out.println();
//        for(int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[i].length; j++) {
//                System.out.println(field[j][i]);
//                System.out.println("x: " + j + " " + "y: " + i);
//                System.out.println();
//            }
//        }
    }


}