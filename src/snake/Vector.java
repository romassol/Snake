package snake;

public class Vector{
    public final int x;
    public final int y;

    Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isOpposite(Vector vector) {
        if (this.equals(Direction.ZERO) || vector.equals(Direction.ZERO))
            return false;

        return (vector.x == this.x && Math.abs(this.y - vector.y) == 2) ||
                (vector.y == this.y && Math.abs(this.x - vector.x) == 2);
    }

    boolean equals(Vector other){
        return x == other.x && y == other.y;
    }

    Vector subtract(Vector other){
        return new Vector(x - other.x, y - other.y);
    }

    Vector sum(Vector other){
        return new Vector(x + other.x, y + other.y);
    }
}
