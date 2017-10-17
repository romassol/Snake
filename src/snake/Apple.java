package snake;

public class Apple extends FieldObject {
    @Override
    public void intersectWithSnake(Game game) {
        game.getCurrentLevel().addSnakePart();
    }
}
