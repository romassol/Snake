package snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Cell {
    private Vector topLeft;
    private Vector bottomRight;
    public int height;
    public int width;
    private List<Vector> passesPositions;
    private HashMap<String, Vector> side;
    private HashMap<String, Vector> direction;
    private HashMap<String, String> oppositeSides;


    public Cell(Vector topLeft, Vector bottomRight){
        passesPositions = new ArrayList<>();
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        recalculateSize();
        side = new HashMap<>();
        side.put("left", getTopLeft());
        side.put("top", getTopLeft());
        side.put("right", getBottomRight());
        side.put("bottom", getBottomRight());
        direction = new HashMap<>();
        direction.put("left", Direction.BOTTOM);
        direction.put("top", Direction.RIGHT);
        direction.put("right", Direction.TOP);
        direction.put("bottom", Direction.LEFT);
    }

    public void addPass(int fieldWidth, int fieldHeight){
    }

    private void recalculateSize(){
        width = bottomRight.x - topLeft.x;
        height = bottomRight.y - topLeft.y;
    }

    public Vector getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Vector topLeft) {
        this.topLeft = topLeft;
        recalculateSize();
    }

    public Vector getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Vector bottomRight) {
        this.bottomRight = bottomRight;
        recalculateSize();
    }

    public List<String> getInternalSides(int fieldWidth, int fieldHeight){
        List<String> sides = new ArrayList<>();
        if (topLeft.y != 0){
            sides.add("top");
        }
        if (topLeft.x != 0){
            sides.add("left");
        }
        if (bottomRight.y != fieldHeight){
            sides.add("bottom");
        }
        if (topLeft.x != fieldWidth){
            sides.add("right");
        }
        return sides;
    }

    public boolean isOtherNeighbour(Cell other) {
        return Math.abs(other.getTopLeft().y - topLeft.y) == height || Math.abs(other.getTopLeft().x - topLeft.x) == width;
    }
}
