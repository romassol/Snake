package snake;


public class Game {
    private Level[] levels;
    private int indexOfCurrentLevel;
    private Vector playerDirection;
    public boolean isGameOver;
    public boolean isWin;

    public void makeTurn() throws MakeTurnException {
        if(isGameOver){
            throw new MakeTurnException();
        }

        FieldObject oldCell = getCurrentLevel()
                .moveSnakeAndReturnOldCell(playerDirection);
        oldCell.intersectWithSnake(this);

        if(isGameOver){
            return;
        }

        if (getCurrentLevel().appleGenerator.isNeedToAdd(oldCell)) {
            if (getCurrentLevel().isOver()) {
                if(isLevelLast()){
                    isWin = true;
                    isGameOver = true;
                    return;
                }
                indexOfCurrentLevel++;
            }
            getCurrentLevel().appleGenerator.generate(getCurrentLevel());
        }
    }

    public Game(Level[] levels) {
        this.levels = levels;
        indexOfCurrentLevel = 0;
        isGameOver = false;
        isWin = false;
    }

    public void setPlayerDirection(Vector playerDirection) {
        this.playerDirection = playerDirection;
    }

    public Level getCurrentLevel() {
        return levels[indexOfCurrentLevel];
    }

    private boolean isLevelLast(){
        return indexOfCurrentLevel == levels.length - 1;
    }
}
