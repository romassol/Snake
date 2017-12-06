package snakeGUI;

import snake.Direction;
import snake.Level;
import snake.Vector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

public class ArrowKeysListener implements KeyListener {
    private MainSnakeWindow parent;
    private Hashtable<Integer, Vector> directionsKeysCode;

    ArrowKeysListener(MainSnakeWindow parent) {
        super();
        this.parent = parent;

        directionsKeysCode = new Hashtable<>();
        directionsKeysCode.put(37, Direction.LEFT);
        directionsKeysCode.put(40, Direction.BOTTOM);
        directionsKeysCode.put(39, Direction.RIGHT);
        directionsKeysCode.put(38, Direction.TOP);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Integer keyCode = e.getKeyCode();
        parent.currentLevel = null;
        if (directionsKeysCode.containsKey(keyCode))
            parent.playerDirection = directionsKeysCode.get(keyCode);
        if (keyCode == 32) {
            if(!parent.timeMachine.empty())
                parent.currentLevel = parent.timeMachine.pop();
            else
                parent.playerDirection = Direction.ZERO;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
