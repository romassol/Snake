package snake;

import java.lang.annotation.ElementType;

@ImageFileName(fileNames = "apple.jpg")
public class Apple implements IFieldObject {
    @Override
    public void intersectWithSnake(Game game) {
        game.getCurrentLevel().addSnakePart();
    }
}
