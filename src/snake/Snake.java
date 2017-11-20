package snake;

import snakeGUI.Settings;

public class Snake {
    private SnakePart tail;
    private SnakeHead head;
    private int juggernautTime;

    public Snake(int x, int y, Vector direction) {
        head = new SnakeHead(x, y, direction, null, null);
        tail = head;
        this.juggernautTime = 0;
    }

    public Snake(SnakePart snakePart) {
        this(snakePart.getX(), snakePart.getY(), snakePart.getDirection());
    }

    public SnakePart addPartAndReturnTail(Vector levelSize) {
        int x = (tail.getX() + tail.getDirection().x * (-1)) % levelSize.x;
        int y = (tail.getY() + tail.getDirection().y * (-1)) % levelSize.y;
        SnakePart newPart = new SnakePart(x, y, tail.getDirection(), tail, null);
        tail.setChild(newPart);
        tail = newPart;
        return newPart;
    }

    public void addPart(SnakePart part) {
        part.setDirection(tail.getDirection());
        part.setParent(tail);
        tail.setChild(part);
        tail = part;
    }

    public void removeTail() {
        SnakePart futureTail = tail.getParent();
        futureTail.setChild(null);
        tail = futureTail;
    }

    public void changeJuggernautTime(int value){
        if (this.juggernautTime > 0)
            this.juggernautTime -= value;
    }

    public Boolean isJuggernaut(){
        return this.juggernautTime > 0;
    }

    public void setJuggernautTime(int millisecTime){
        this.juggernautTime = millisecTime;
    }

    public SnakeHead getHead() {
        return head;
    }

    public SnakePart getTail() {
        return tail;
    }
}
