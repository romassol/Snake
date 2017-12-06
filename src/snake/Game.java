package snake;


import java.util.Stack;

public class Game {
    private Level[] levels;
    private int currentLevelIndex;
    private Vector playerDirection;
    private boolean isGameOver;
    private boolean isWin;
    private Stack<Level> timeMachine;

    public void makeTurn() throws TurnException {
        if(isGameOver) {
            throw new TurnException();
        }

        IFieldObject oldCell = getCurrentLevel()
                .moveSnakeAndReturnOldCell(playerDirection);
        oldCell.intersectWithSnake(this);

        if(isGameOver) {
            return;
        }
        if (getCurrentLevel().getTeleportGenerator().isNeedToAdd(oldCell)) {
            getCurrentLevel().getTeleportGenerator().generate(getCurrentLevel());
        }

        if (getCurrentLevel().getJuggernautGenerator().isNeedToAdd()) {
            getCurrentLevel().getJuggernautGenerator().generate(getCurrentLevel());
        }

        if (getCurrentLevel().appleGenerator.isNeedToAdd(oldCell)) {
            getCurrentLevel().appleGenerator.generate(getCurrentLevel());
        }

        if (getCurrentLevel().isOver()) {
            if(isLevelLast()) {
                isWin = true;
                isGameOver = true;
                return;
            }
            currentLevelIndex++;
        }
    }

    public Game(Level[] levels) {
        this.levels = levels;
        currentLevelIndex = 0;
        isGameOver = false;
        isWin = false;
        timeMachine = new Stack<>();
    }

    public void setPlayerDirection(Vector playerDirection) {
        this.playerDirection = playerDirection;
    }

    public Level getCurrentLevel() {
        return levels[currentLevelIndex];
    }
    public void setCurrentLevel(Level level){
        levels[currentLevelIndex] = level;
    }

    private boolean isLevelLast() {
        return currentLevelIndex == levels.length - 1;
    }

    public Stack<Level> getTimeMachine() {
        return timeMachine;
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public void setIsGameOver(boolean isGameOver){
        this.isGameOver = isGameOver;
    }

    public boolean getIsWin(){
        return isWin;
    }

    public void setIsWin(boolean isWin){
        this.isWin = isWin;
    }
}
