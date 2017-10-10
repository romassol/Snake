package snake;

public class Apple extends FieldObject {
    @Override
    public void intersectWithSnake(Game game) throws Exception {
        game.getLevel().addSnakePart();
    }
}
