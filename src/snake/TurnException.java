package snake;

public class TurnException extends Exception {
    public TurnException() {
        super("Can't make a turn - the game is over");
    }
}
