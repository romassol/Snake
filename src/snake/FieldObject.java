package snake;

public abstract class FieldObject {
    public Integer x;
    public Integer y;

    public FieldObject(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public abstract void intersectWithSnake(Game game) throws Exception;
}
