package snake;

public abstract class FieldObject {
    public int x;
    public int y;

    public FieldObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void intersectWithSnake(Game game) throws Exception;
}
