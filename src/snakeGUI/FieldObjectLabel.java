package snakeGUI;

import snake.FieldObject;

import javax.swing.*;
import java.awt.*;

public class FieldObjectLabel extends JLabel {
    private FieldObject parent;

    FieldObjectLabel(FieldObject parent, int size, ImageSaver imageSaver) {
        super();

        this.parent = parent;

        setPreferredSize(new Dimension(size, size));
        setIcon(imageSaver.getIcon(parent));
        setVisible(true);
    }
}
