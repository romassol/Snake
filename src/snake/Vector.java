package snake;

public class Vector {
    public final int DELTA_X;
    public final int DELTA_Y;

    Vector(int deltaX, int deltaY) {
        DELTA_X = deltaX;
        DELTA_Y = deltaY;
    }

    public Vector copy() {
        return new Vector(this.DELTA_X, this.DELTA_Y);
    }
}
