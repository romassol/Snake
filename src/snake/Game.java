package snake;

import jdk.jshell.spi.ExecutionControl;

public class Game {
    public Field field;
    public Snake snake;
    public  AppleGenerator appleGenerator;

    public void tick() { }

    public boolean gameOver() {
        return true;
    }
}
