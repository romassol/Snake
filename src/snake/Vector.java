package snake;

public class Vector{
    public final int X;
    public final int Y;

    Vector(int x, int y) {
        X = x;
        Y = y;
    }

    boolean isOpposite(Vector vector) {
        if (this.equals(Direction.ZERO) || vector.equals(Direction.ZERO))
            return false;

        return (vector.X == this.X && Math.abs(this.Y - vector.Y) == 2) ||
                (vector.Y == this.Y && Math.abs(this.X - vector.X) == 2);
    }

    boolean equals(Vector other){
        return X == other.X && Y == other.Y;
    }

    Vector subtract(Vector other){
        return new Vector(X - other.X, Y - other.Y);
    }

    Vector sum(Vector other){
        return new Vector(X + other.X, Y + other.Y);
    }
}
