package snake;

public interface IObjectCreator {
    FieldObject createFieldObject(
            int x,
            int y,
            Vector vector,
            SnakePart parent,
            SnakePart child);
}