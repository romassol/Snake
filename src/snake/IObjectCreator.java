package snake;

public interface IObjectCreator {
    IFieldObject createFieldObject(
            int x,
            int y,
            Vector vector,
            SnakePart parent,
            SnakePart child);
}