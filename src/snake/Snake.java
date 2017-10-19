package snake;

public class Snake {
    private SnakePart tail;
    private SnakePart head;

    public Snake(int x, int y, Vector direction) {
        initialize(x, y, direction);
    }

    public Snake(SnakePart snakePart) {
        initialize(snakePart.getX(), snakePart.getY(), snakePart.getDirection());
    }

    private void initialize(int x, int y, Vector direction) {
        head = new SnakePart(x, y, direction, null, null);
        tail = head;
    }

    public SnakePart addPartAndReturnTail() {
        int x = tail.getX() + tail.getDirection().DELTA_X * (-1);
        int y = tail.getY() + tail.getDirection().DELTA_Y * (-1);
        SnakePart newPart = new SnakePart(x, y, tail.getDirection(), tail, null);
        tail.setChild(newPart);
        tail = newPart;
        return newPart;
    }

    public void addPart(SnakePart part){
        part.setDirection(tail.getDirection());
        part.setParent(tail);
        tail.setChild(part);
        tail = part;
    }

    public void removeTail(){
        SnakePart futureTail = tail.getParent();
        futureTail.setChild(null);
        tail = futureTail;
    }

    public SnakePart getHead() {
        return head;
    }

    public SnakePart getTail() {
        return tail;
    }
}
