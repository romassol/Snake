import org.junit.Test;
import snake.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LevelGeneratorTest {
    @Test
    public void createAndGetLevel1() throws Exception {
        for (int x = 0; x < 100; x++){
            createLevelFile();
        }
    }

    @Test
    public void createLevelFile() throws Exception {
        LevelGenerator generator = new LevelGenerator();
        int deadlockCount = 0;
        Level testLevel = generator.createAndGetLevel("fortest.txt", 8);
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
}