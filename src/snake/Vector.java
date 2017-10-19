package snake;

public class Vector implements Cloneable{
    public final int DELTA_X;
    public final int DELTA_Y;

    Vector(int deltaX, int deltaY) {
        DELTA_X = deltaX;
        DELTA_Y = deltaY;
    }

    public Vector clone() {
        return new Vector(this.DELTA_X, this.DELTA_Y);
    }

    public boolean isOpposite(Vector vector) {
        return !this.isEqualWithOther(Direction.ZERO) &&
                !vector.isEqualWithOther(Direction.ZERO) &&
                (Math.abs(this.DELTA_X - vector.DELTA_X) == 2 ||
                        Math.abs(this.DELTA_Y - vector.DELTA_Y) == 2);
    }

    public boolean isEqualWithOther(Vector other){
        return DELTA_X == other.DELTA_X && DELTA_Y == other.DELTA_Y;
    }

    public Vector substractOtherFromThis(Vector other){
        return new Vector(DELTA_X - other.DELTA_X, DELTA_Y - other.DELTA_Y);
    }

    public Vector summarizeOtherWithThis(Vector other){
        return new Vector(DELTA_X + other.DELTA_X, DELTA_Y + other.DELTA_Y);
    }
}
