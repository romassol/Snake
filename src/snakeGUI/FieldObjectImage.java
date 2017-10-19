package snakeGUI;

import snake.FieldObject;

import javax.swing.*;
import java.awt.*;

class FieldObjectImage extends JLabel {
    FieldObjectImage(FieldObject parent, int size, ImageSaver imageSaver) {
        super();

        setPreferredSize(new Dimension(size, size));
        setIcon(imageSaver.getIcon(parent));
        setVisible(true);
    }
}
