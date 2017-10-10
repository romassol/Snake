package snake;

public class Wall extends FieldObject {
    @Override
    public void intersectWithSnake(Game game) {
        game.isGameOver = true;
    }
}
