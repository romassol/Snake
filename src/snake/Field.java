package snake;

import java.util.Objects;

public class Field {
    public FieldObject[][] objects;
    public Snake snake;

    public Field(Integer width, Integer height) {
        objects = new FieldObject[width][height];
    }
}
