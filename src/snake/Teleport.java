package snake;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ImageFileName(fileNames = "teleport.jpg")
public class Teleport implements IFieldObject {
    @Override
    public void intersectWithSnake(Game game) {
        game.getCurrentLevel().getTeleports().remove(this);
        Random random = new Random();
        List<Teleport> teleports = new ArrayList<>(game.getCurrentLevel().getTeleports().keySet());
        int index = random.nextInt(game.getCurrentLevel().getTeleports().size());
        Vector futureTeleportPosition = game.getCurrentLevel().getTeleports().get(teleports.get(index));
        Vector direction = futureTeleportPosition.subtract(game.getCurrentLevel().getSnake().getHead().getPosition());
        Vector playerDirection = game.getCurrentLevel().getSnake().getHead().getDirection();
        game.getCurrentLevel().getSnake().getHead().setDirection(Direction.ZERO);
        IFieldObject oldObject = game.getCurrentLevel().moveSnakeAndReturnOldCell(direction);
        game.getCurrentLevel().getSnake().getHead().setDirection(playerDirection);
        Teleport futureTeleport = (Teleport) oldObject;
        game.getCurrentLevel().getTeleports().remove(futureTeleport);
    }
}
