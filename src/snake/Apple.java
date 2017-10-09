package snake;

public class Apple extends FieldObject {

    public Apple(int x, int y) {
        super(x, y);
    }

    @Override
    public void intersectWithSnake(Game game) throws Exception {
        game.level.addSnakePart();
    }
}
