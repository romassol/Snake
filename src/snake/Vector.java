package snake;

public class Vector implements Cloneable{
    final int DELTA_X;
    final int DELTA_Y;

    Vector(int deltaX, int deltaY) {
        DELTA_X = deltaX;
        DELTA_Y = deltaY;
    }

    public Vector clone() {
        return new Vector(this.DELTA_X, this.DELTA_Y);
    }

    boolean isOpposite(Vector vector) {
        return !this.isEqualWithOther(Direction.ZERO) &&
                !vector.isEqualWithOther(Direction.ZERO) &&
                (Math.abs(this.DELTA_X - vector.DELTA_X) == 2 ||
                        Math.abs(this.DELTA_Y - vector.DELTA_Y) == 2);
    }

    boolean isEqualWithOther(Vector other){
        return DELTA_X == other.DELTA_X && DELTA_Y == other.DELTA_Y;
    }

    Vector subtractOtherFromThis(Vector other){
        return new Vector(
                DELTA_X - other.DELTA_X,
                DELTA_Y - other.DELTA_Y);
    }

    Vector summarizeOtherWithThis(Vector other){
        return new Vector(
                DELTA_X + other.DELTA_X,
                DELTA_Y + other.DELTA_Y);
    }
}
