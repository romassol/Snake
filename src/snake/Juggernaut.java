package snake;

@ImageFileName(fileNames = "juggernaut.jpg")
public class Juggernaut implements IFieldObject{
    @Override
    public void intersectWithSnake(Game game) {
        game.getCurrentLevel().getSnake().setJuggernautTime(5000);
    }
}
