package snake;

public class Vector implements Cloneable{
    final int X;
    final int Y;

    Vector(int x, int y) {
        X = x;
        Y = y;
    }

    public Vector clone() {
        return new Vector(this.X, this.Y);
    }

    boolean isOpposite(Vector vector) {
        return !this.isEqualWithOther(Direction.ZERO) &&
                !vector.isEqualWithOther(Direction.ZERO) &&
                (Math.abs(this.X - vector.X) == 2 ||
                        Math.abs(this.Y - vector.Y) == 2);
    }

    boolean isEqualWithOther(Vector other){
        return X == other.X && Y == other.Y;
    }

    Vector subtract(Vector other){
        return new Vector(X - other.X, Y - other.Y);
    }

    Vector sum(Vector other){
        return new Vector(X + other.X, Y + other.Y);
    }
}
