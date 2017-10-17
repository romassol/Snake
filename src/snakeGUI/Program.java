package snakeGUI;

import snake.FieldReader;
import snake.Game;
import snake.Level;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Program {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IOException, IllegalAccessException {
        Game game = new Game(new Level(new FieldReader("level2.txt"), 4));
        MainSnakeWindow window = new MainSnakeWindow(game);
    }
}
