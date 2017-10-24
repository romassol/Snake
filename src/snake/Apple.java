package snake;

@ImageFileName(fileName = "apple.jpg")
public class Apple implements IFieldObject {
    @Override
    public void intersectWithSnake(Game game) {
        game.getCurrentLevel().addSnakePart();
    }
}
