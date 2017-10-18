package snake;

public class
SnakePart extends FieldObject {
    public Vector direction;
    public SnakePart parent;
    public SnakePart child;
    private Vector position;

    public SnakePart(
            int x,
            int y,
            Vector direction,
            SnakePart parent,
            SnakePart child) {
        setPosition(x, y);
        this.direction = direction;
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void intersectWithSnake(Game game) {
        game.isGameOver = true;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position = new Vector(x, y);
    }

    public int getX(){
        return position.DELTA_X;
    }

    public int getY(){
        return position.DELTA_Y;
    }
}
