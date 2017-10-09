package snake;

public class Wall extends FieldObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void intersectWithSnake(Game game) {
        game.isGameOver = true;
    }
}
