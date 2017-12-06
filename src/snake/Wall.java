package snake;

@ImageFileName(fileNames = "wall.jpg")
public class Wall implements IFieldObject {
    @Override
    public void intersectWithSnake(Game game) {
        if (!game.getCurrentLevel().getSnake().isJuggernaut()){
            game.setIsGameOver(true);
        }
    }
}
