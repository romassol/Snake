package snakeGUI;

import snake.IFieldObject;

import javax.swing.*;
import java.awt.*;

class FieldObjectImage extends JLabel {
    FieldObjectImage(IFieldObject parent, int size, ImageSaver imageSaver) {
        super();

        setPreferredSize(new Dimension(size, size));
        setIcon(imageSaver.getIcon(parent));
        setVisible(true);
    }
}
