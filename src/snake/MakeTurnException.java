package snake;

public class MakeTurnException extends Exception {
    public MakeTurnException(){
        super("Can't make a turn - the game is over");
    }
}
