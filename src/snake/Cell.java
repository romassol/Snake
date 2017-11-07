package snake;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Cell {
    private Vector topLeft;
    private Vector bottomRight;
    public Cell internalCell;
    public int height;
    public int width;


    public Cell(Vector topLeft, Vector bottomRight, Cell internalCell){
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.internalCell = internalCell;
        if(internalCell!=null) {
            this.internalCell.recalculateSize();
        }
        recalculateSize();
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
}
