package snake;

public class Snake {
    public SnakePart tail;
    public SnakePart head;

    public Snake(Integer x, Integer y, Vector direction) {
        head = new SnakePart(x, y, direction, null, null);
        tail = new SnakePart(x, y, direction, head, null);
    }
}
