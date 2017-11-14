package snake;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Cell {
    private Vector topLeft;
    private Vector bottomRight;
    private Cell internalCell;
    public int height;
    public int width;


    public Cell(Vector topLeft, Vector bottomRight){
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.internalCell = null;
//        this.internalCell.recalculateSize();
//        if(internalCell!=null) {
//            this.internalCell.recalculateSize();
//        }
        recalculateSize();
    }

    private void recalculateSize(){
        width = bottomRight.x - topLeft.x + 1;
        height = bottomRight.y - topLeft.y + 1;
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

    public void setInternalCell(Cell internallCell){
        this.internalCell = internallCell;
        this.internalCell.recalculateSize();
    }

    public Cell getInternalCell(){
        return internalCell;
    }
}
