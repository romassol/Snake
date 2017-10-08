package snake;

public class Vector {
    public final Integer DELTA_X;
    public final Integer DELTA_Y;

    Vector(Integer deltaX, Integer deltaY) {
        DELTA_X = deltaX;
        DELTA_Y = deltaY;
    }

    public Vector copy() {
        return new Vector(this.DELTA_X, this.DELTA_Y);
    }
}
