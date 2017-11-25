package snakeGUI;

import snake.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Program {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IOException, IllegalAccessException {
        Level[] levels = new Level[1];

        LevelGenerator level = new LevelGenerator();
        //Level newLevel = new Level(new FieldReader("fortest.txt"), 4);
        Level newLevel = level.createAndGetLevel("level8.txt", 8);
        levels[0] = newLevel;
        Game game = new Game(levels);
        MainSnakeWindow window = new MainSnakeWindow(game);
    }
}
