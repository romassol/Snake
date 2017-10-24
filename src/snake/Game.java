package snake;


public class Game {
    private Level[] levels;
    private int currentLevelIndex;
    private Vector playerDirection;
    public boolean isGameOver;
    public boolean isWin;

    public void makeTurn() throws TurnException {
        if(isGameOver) {
            throw new TurnException();
        }

        FieldObject oldCell = getCurrentLevel()
                .moveSnakeAndReturnOldCell(playerDirection);
        oldCell.intersectWithSnake(this);

        if(isGameOver) {
            return;
        }

        if (!getCurrentLevel().appleGenerator.isNeedToAdd(oldCell)) {
            return;
        }
        if (getCurrentLevel().isOver()) {
            if(isLevelLast()) {
                isWin = true;
                isGameOver = true;
                return;
            }
            currentLevelIndex++;
        }
        getCurrentLevel().appleGenerator.generate(getCurrentLevel());
    }

    public Game(Level[] levels) {
        this.levels = levels;
        currentLevelIndex = 0;
        isGameOver = false;
        isWin = false;
    }

    public void setPlayerDirection(Vector playerDirection) {
        this.playerDirection = playerDirection;
    }

    public Level getCurrentLevel() {
        return levels[currentLevelIndex];
    }

    private boolean isLevelLast() {
        return currentLevelIndex == levels.length - 1;
    }
}
