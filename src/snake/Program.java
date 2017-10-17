package snake;

import java.util.HashMap;

public class Program {
    public static void main(String[] args) {
        HashMap<Character, IObjectCreator> dict = new HashMap<>();
        dict.put('#', (x, y, vector, parent, child) -> new Wall());
        dict.put(' ', (x, y, vector, parent, child) -> new Empty());
        dict.put('A', (x, y, vector, parent, child) -> new Apple());
        dict.put('S', SnakePart::new);

        FieldObject a = dict.get('S').createFieldObject(
                4, 6, Direction.RIGHT, null, null);
    }
}
