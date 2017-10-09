package snake;

public class SnakePart extends FieldObject {
    public Vector direction;
    public SnakePart parent;
    public SnakePart child;

    public SnakePart(
            int x,
            int y,
            Vector direction,
            SnakePart parent,
            SnakePart child) {
        super(x, y);
        this.direction = direction;
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void intersectWithSnake(Game game) {
        game.isGameOver = true;
    }
}
