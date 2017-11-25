package snake.tests;

import org.junit.Test;
import snake.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LevelGeneratorTest {
    @Test
    public void createAndGetLevel1() throws Exception {
        for (int x = 0; x < 1000; x++){
            createLevelFile();
        }
    }

    @Test
    public void createLevelFile() throws Exception {
        LevelGenerator generator = new LevelGenerator();
        int deadlockCount = 0;
        Level testLevel = generator.createAndGetLevel("fortest.txt", 8, 0);
        for (int x = 0; x < testLevel.getLevelSize().x; x++){
            for (int y = 0; y < testLevel.getLevelSize().y; y++){
                if (!(testLevel.getFieldObject(x, y) instanceof Wall)){
                    int countOfEmptyNeighbours = 0;
                    Vector current = new Vector(x, y);
                    Vector[] neighbours = new Vector[4];
                    neighbours[0] = current
                            .sum(Direction.RIGHT)
                            .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);
                    neighbours[1] = current
                            .sum(Direction.LEFT)
                            .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);
                    neighbours[2] = current
                            .sum(Direction.TOP)
                            .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);
                    neighbours[3] = current
                            .sum(Direction.BOTTOM)
                            .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);
                    for (Vector neighbour : neighbours)
                        countOfEmptyNeighbours += !(testLevel.getFieldObject(neighbour.x, neighbour.y)
                                instanceof Wall) ? 1 : 0;
                    deadlockCount += countOfEmptyNeighbours <= 1 ? 1 : 0;
                }
            }
        }
        assertEquals(0, deadlockCount);
    }

    @Test
    public void checkLevelsClosedRooms() throws Exception{
        for (int x = 0; x < 1000; x++){
            checkLevelClosedRooms();
        }
    }

    @Test
    public void checkLevelClosedRooms() throws Exception{
        LevelGenerator generator = new LevelGenerator();
        Level testLevel = generator.createAndGetLevel("fortest.txt", 8, 0);
        ArrayList<Vector> empties = new ArrayList();
        for (int x = 0; x < testLevel.getLevelSize().x; x++) {
            for (int y = 0; y < testLevel.getLevelSize().y; y++) {
                if (!(testLevel.getFieldObject(x, y) instanceof Wall))
                    empties.add(new Vector(x, y));
            }
        }
        ArrayList<Vector> passed = new ArrayList<>();
        ArrayList<Vector> queue = new ArrayList<>();
        passed.add(empties.get(0));
        queue.add(empties.get(0));
        while (queue.size() != 0)
        {
            Vector point = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);

            Vector[] neighbours = new Vector[4];
            neighbours[0] = point
                    .sum(Direction.RIGHT)
                    .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);
            neighbours[1] = point
                    .sum(Direction.LEFT)
                    .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);
            neighbours[2] = point
                    .sum(Direction.TOP)
                    .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);
            neighbours[3] = point
                    .sum(Direction.BOTTOM)
                    .looping(testLevel.getLevelSize().x, testLevel.getLevelSize().y);

            for (Vector neighbour : neighbours) {
                if (!(testLevel.getFieldObject(neighbour.x, neighbour.y) instanceof Wall)) {
                    boolean notInpassed = true;
                    for (Vector pass : passed)
                        if (pass.equals(neighbour))
                            notInpassed = false;
                    if (notInpassed) {
                        passed.add(neighbour);
                        queue.add(neighbour);
                    }

                }
            }
        }
        StringBuilder strField = new StringBuilder();
        if (empties.size() != passed.size()) {
            for (int y = 0; y < testLevel.getLevelSize().y; y++) {
                for (int x = 0; x < testLevel.getLevelSize().x; x++) {
                    if (!(testLevel.getFieldObject(x, y) instanceof Wall))
                        strField.append("#");
                    else
                        strField.append(".");
                }
                System.out.println(strField);
                strField = new StringBuilder();
            }
        }
        assertEquals(empties.size(), passed.size());
    }

    @Test
    public void testSnakeOnFields() throws Exception{
        for (int x = 0; x < 1000; x++){
            testSnakeOnField();
        }
    }

    @Test
    public void testSnakeOnField() throws Exception {
        LevelGenerator generator = new LevelGenerator();
        Level testLevel = generator.createAndGetLevel("fortest.txt", 8, 0);
        boolean snakeOnField = false;
        for (int y = 0; y < testLevel.getLevelSize().y; y++) {
            for (int x = 0; x < testLevel.getLevelSize().x; x++) {
                if (testLevel.getFieldObject(x, y) instanceof SnakeHead)
                    snakeOnField = true;
            }
        }
        assertEquals(true, snakeOnField);
    }
}