package snakeGUI;

import snake.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class Program {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IOException, IllegalAccessException {
        Level[] levels = new Level[1];

        LevelGenerator level = new LevelGenerator();
        Level newLevel = new Level(new FieldReader("level9.txt"), 4, 5, 2);
//        Level newLevel = level.createAndGetLevel("level8.txt", 8, 2);
        levels[0] = newLevel;
        Game game = new Game(levels);
        MainSnakeWindow window = new MainSnakeWindow(game);
    }
}
