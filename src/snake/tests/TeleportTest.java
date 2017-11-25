package snake.tests;

import org.junit.Test;
import snake.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TeleportTest {
    @Test
    public void intersectWithSnake() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IOException, IllegalAccessException, TurnException {
        LevelGenerator generator = new LevelGenerator();
        Level[] levels = new Level[1];
        for(int i = 0; i < 1000; i++){
            Level level  = generator.createAndGetLevel("teleportsTests.txt.txt", 8, 2);
            levels[0] = level;
            Game game = new Game(levels);
            List<Teleport> teleports = new ArrayList<>(level.getTeleports().keySet());
            Vector direction = level.getTeleports().get(teleports.get(0)).subtract(level.getSnake().getHead().getPosition());
            Vector futurePosition = level.getTeleports().get(teleports.get(1));
            Teleport input = (Teleport) level.moveSnakeAndReturnOldCell(direction);
            input.intersectWithSnake(game);
            assertEquals(futurePosition.x, level.getSnake().getHead().getPosition().x);
            assertEquals(futurePosition.y, level.getSnake().getHead().getPosition().y);
        }
    }
}
