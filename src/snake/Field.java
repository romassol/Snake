package snake;

public class Field {
    public FieldObject[][] objects;

    public Field(Integer width, Integer height) {
        objects = new FieldObject[width][height];
    }
}
