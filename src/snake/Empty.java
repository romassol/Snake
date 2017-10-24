package snake;

@ImageFileName(fileName = "empty.jpg")
public class Empty implements IFieldObject {
    @Override
    public void intersectWithSnake(Game game) {
    }
}
