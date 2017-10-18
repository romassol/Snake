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
    private Map<Character, IObjectCreator> characterSymbol;
    private FieldObject[][] objects;
    private List<SnakePart> snakeParts;
    private SnakePart head;
    private Snake snake;

    public FieldReader(String fileName) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException,
            InvocationTargetException, IOException {
        this.fileName = fileName;
        snakeParts = new ArrayList<>();
        characterSymbol = getCharacterSymbol();
        fillObjects();
        createSnake();
        setDirection();
    }

    private HashMap<Character, IObjectCreator> getCharacterSymbol(){
        HashMap<Character, IObjectCreator> characterSymbol = new HashMap<>();
        characterSymbol.put('#', (x, y, vector, parent, child) -> new Wall());
        characterSymbol.put(' ', (x, y, vector, parent, child) -> new Empty());
        characterSymbol.put('A', (x, y, vector, parent, child) -> new Apple());
        characterSymbol.put('S', SnakePart::new);
        characterSymbol.put('H', SnakePart::new);
        return characterSymbol;
    }

    private void fillObjects() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException, IOException {

        List<String> lines = Files.readAllLines(
                Paths.get(Settings.levelUrl+fileName),
                StandardCharsets.UTF_8);

        try {
            objects = new FieldObject[lines.size()][lines.get(0).length()];
        } catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Level is incorrect");
        }
        for(int i = 0; i < lines.size(); i++){
            for (int j = 0; j < lines.get(i).length(); j++){
                Character symbol = lines.get(i).charAt(j);
                objects[i][j] = characterSymbol.get(symbol).createFieldObject(j, i, null,null,null);
                if(symbol == 'S'){
                    snakeParts.add((SnakePart) objects[i][j]);
                }
                if(symbol == 'H'){
                    head = (SnakePart) objects[i][j];
                }
            }
        }
    }

    private void createSnake(){
        Snake snake = new Snake(head);
        List<SnakePart> neighbors = getNearbySnakeParts(getNeighbours(head));
        getSnakePart(neighbors, snake);
        this.snake = snake;
    }

    private List<SnakePart> getNearbySnakeParts(List<FieldObject> neighbours){
        List<SnakePart> nearbySnakeParts = new ArrayList<>();
        for (FieldObject neighbour : neighbours) {
            if (neighbour instanceof SnakePart && snakeParts.contains(neighbour)) {
                nearbySnakeParts.add((SnakePart) neighbour);
            }
        }
        return nearbySnakeParts;
    }

    private void getSnakePart(List<SnakePart> nearbySnakeParts, Snake snake){
        SnakePart next;
        for (SnakePart nearbySnakePart : nearbySnakeParts) {
            next = nearbySnakePart;
            snake.addPart(next);
            snakeParts.remove(next);
            List<SnakePart> tmp = getNearbySnakeParts(getNeighbours(nearbySnakePart));
            getSnakePart(tmp, snake);
        }
        if(snakeParts.size()!=0){
            snakeParts.add(snake.tail);
            snake.removeTail();
            return;
        }
    }

    private List<FieldObject> getNeighbours(SnakePart center){
        List<Vector> offset = Arrays.asList(Direction.LEFT, Direction.RIGHT, Direction.BOTTOM, Direction.TOP);
        List<FieldObject> neighbours = new ArrayList<>();
        int x;
        int y;
        for (Vector anOffset : offset) {
            x = center.getX() + anOffset.DELTA_X;
            y = center.getY() + anOffset.DELTA_Y;
            if (x >= 0 && y >= 0 && x <= objects[0].length && y <= objects.length) {
                neighbours.add(objects[y][x]);
            }
        }
        return neighbours;
    }

    private void setDirection(){
        SnakePart current = snake.head;
        SnakePart next = snake.head.child;
        while (next != null) {
            int x = current.getX() - next.getX();
            int y = current.getY() - next.getY();
            current.direction = new Vector(x, y);
            current = next;
            next = current.child;

        }
        current.direction = Direction.BOTTOM;
    }

    public FieldObject[][] getObjects() {
        return objects;
    }

    public Snake getSnake() {
        return snake;
    }
}
