package snake;

public class Snake {
    public SnakePart tail;
    public SnakePart head;

    public Snake(int x, int y, Vector direction) {
        initialize(x, y, direction);
    }

    public Snake(SnakePart snakePart) {
        initialize(snakePart.getX(), snakePart.getY(), snakePart.direction);
    }

    private void initialize(int x, int y, Vector direction) {
        head = new SnakePart(x, y, direction, null, null);
        tail = head;
    }

    public SnakePart addPartAndReturnTail() {
        int x = tail.getX() + tail.direction.DELTA_X * (-1);
        int y = tail.getY() + tail.direction.DELTA_Y * (-1);
        SnakePart newPart = new SnakePart(x, y, tail.direction, tail, null);
        tail.child = newPart;
        tail = newPart;
        return newPart;
    }

    public void addPart(SnakePart part){
        part.direction = tail.direction;
        part.parent = tail;
        tail.child = part;
        tail = part;
    }

    public void removeTail(){
        SnakePart futureTail = tail.parent;
        futureTail.child = null;
        tail = futureTail;
    }
}
