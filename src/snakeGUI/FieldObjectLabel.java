package snakeGUI;

import snake.FieldObject;

import javax.swing.*;
import java.awt.*;

public class FieldObjectLabel extends JLabel {
    private FieldObject parent;

    FieldObjectLabel(FieldObject parent, int size) {
        super();

        this.parent = parent;

        setPreferredSize(new Dimension(size, size));
        setIcon(ImageSaver.getIcon(parent));
        setVisible(true);
    }
}
