package snake;

public class
SnakePart extends FieldObject {
    public Vector direction;
    public SnakePart parent;
    public SnakePart child;
    private int x;
    private int y;

    public SnakePart(
            int x,
            int y,
            Vector direction,
            SnakePart parent,
            SnakePart child) {
        setX(x);
        setY(y);
        this.direction = direction;
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void intersectWithSnake(Game game) {
        game.isGameOver = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0)
            throw new IllegalArgumentException(
                    "Coordinate must have be more than zero");
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0)
            throw new IllegalArgumentException(
                    "Coordinate must have be more than zero");
        this.y = y;
    }
}
