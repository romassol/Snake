package snake;

@ImageFileName(fileNames = "wall.jpg")
public class Wall implements IFieldObject {
    @Override
    public void intersectWithSnake(Game game) {
        game.isGameOver = true;
    }
}
