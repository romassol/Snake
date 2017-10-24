package snake;

import snakeGUI.Settings;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class FieldReader {
    private String fileName;
    private static HashMap<Character, IObjectCreator> characterToFieldObject;
    private FieldObject[][] field;
    private Snake snake;

    static {
        characterToFieldObject = new HashMap<>();
        characterToFieldObject.put('#',
                (x, y, vector, parent, child) -> new Wall());
        characterToFieldObject.put('.',
                (x, y, vector, parent, child) -> new Empty());
        characterToFieldObject.put('A',
                (x, y, vector, parent, child) -> new Apple());
        characterToFieldObject.put('S', SnakePart::new);
        characterToFieldObject.put('H', SnakePart::new);
    }

    public FieldReader(String fileName) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException,
            InvocationTargetException, IOException {
        this.fileName = fileName;
        fillFieldAndCreateSnake();
        fillSnakePartsDirections();
    }

    private void fillFieldAndCreateSnake() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException, IOException {

        List<String> lines = Files.readAllLines(
                Paths.get(Settings.levelUrl+fileName),
                StandardCharsets.UTF_8);

        try {
            field = new FieldObject[lines.size()][lines.get(0).length()];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Level is incorrect");
        }
        List<SnakePart> snakeParts = new ArrayList<>();
        SnakePart head = null;
        for(int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                Character symbol = lines.get(i).charAt(j);
                field[i][j] = characterToFieldObject.get(symbol)
                        .createFieldObject(
                                j, i, null,null,null);
                if(symbol == 'S') {
                    snakeParts.add((SnakePart) field[i][j]);
                }
                if(symbol == 'H') {
                    head = (SnakePart) field[i][j];
                }
            }
        }
        createSnake(head,snakeParts);
    }

    private void createSnake(SnakePart head, List<SnakePart> snakeParts) {
        Snake snake = new Snake(head);
        List<SnakePart> neighbors =
                getNearbySnakeParts(getNeighbours(head), snakeParts);
        constructSnake(neighbors, snake, snakeParts);
        this.snake = snake;
    }

    private List<SnakePart> getNearbySnakeParts(
            List<FieldObject> neighbours,
            List<SnakePart> snakeParts) {
        List<SnakePart> nearbySnakeParts = new ArrayList<>();
        for (FieldObject neighbour : neighbours) {
            if (neighbour instanceof SnakePart && snakeParts.contains(neighbour)) {
                nearbySnakeParts.add((SnakePart) neighbour);
            }
        }
        return nearbySnakeParts;
    }

    private void constructSnake(
            List<SnakePart> nearbySnakeParts,
            Snake snake,
            List<SnakePart> snakeParts){
        SnakePart next;
        for (SnakePart nearbySnakePart : nearbySnakeParts) {
            next = nearbySnakePart;
            snake.addPart(next);
            snakeParts.remove(next);
            List<SnakePart> tmp = getNearbySnakeParts(
                    getNeighbours(nearbySnakePart),
                    snakeParts);
            constructSnake(tmp, snake, snakeParts);
        }
        if(snakeParts.size()!=0) {
            snakeParts.add(snake.getTail());
            snake.removeTail();
        }
    }

    private List<FieldObject> getNeighbours(SnakePart center) {
        List<Vector> offset = Arrays.asList(
                Direction.LEFT,
                Direction.RIGHT,
                Direction.BOTTOM,
                Direction.TOP);

        List<FieldObject> neighbours = new ArrayList<>();
        for (Vector anOffset : offset) {
            Vector neighbour = center.getPosition().sum(anOffset);
            if (neighbour.X >= 0 && neighbour.Y >= 0 &&
                    neighbour.X <= field[0].length &&
                    neighbour.Y <= field.length) {
                neighbours.add(field[neighbour.Y][neighbour.X]);
            }
        }
        return neighbours;
    }

    private void fillSnakePartsDirections() {
        SnakePart current = snake.getHead();
        SnakePart next = snake.getHead().getChild();
        while (next != null) {
            current.setDirection(
                    current
                        .getPosition()
                        .subtract(next.getPosition()));
            current = next;
            next = current.getChild();

        }
        current.setDirection(Direction.ZERO);
    }

    public FieldObject[][] getField() {
        return field;
    }

    public Snake getSnake() {
        return snake;
    }
}
