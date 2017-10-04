package snake;

public class SnakePart extends FieldObject {
    public Direction direction;
    public SnakePart next;

    public SnakePart(Integer x, Integer y, Direction direction, SnakePart next) {
        super(x, y);
        this.direction = direction;
        this.next = next;
    }

    @Override
    public void intersectWithSnake() {

    }
}
