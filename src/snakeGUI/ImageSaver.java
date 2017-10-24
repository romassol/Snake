package snakeGUI;

import javafx.util.Pair;
import snake.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;

public class ImageSaver {
    private HashMap<String, ImageIcon> images;
    private static Pair<String, ImageIcon> defaultImage;
    private int cellSize;

    public ImageSaver(int cellSize) {
        setCellSize(cellSize);
        images = new HashMap<>();
        defaultImage = new Pair<>(
            Settings.imageUrl + Settings.defaultImageFileName,
            getImageIcon(Settings.defaultImageFileName)
        );
    }

    private ImageIcon getImageIcon(String fileName) {
        Image img = new ImageIcon(Settings.imageUrl + fileName)
                .getImage()
                .getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon();
        imageIcon.setImage(img);

        return imageIcon;
    }

    public ImageIcon getIcon(FieldObject obj) {
        String className = obj.getClass().getSimpleName().toLowerCase();

        if (images.containsKey(className))
            return images.get(className);

        ImageFileName annotation = obj.getClass().getAnnotation(ImageFileName.class);

        if (annotation == null) {
            images.put(defaultImage.getKey(), defaultImage.getValue());
            return defaultImage.getValue();
        }

        String fileName = annotation.fileName();
        ImageIcon fileImage;
        if (!new File(Settings.imageUrl + fileName).exists())
            fileImage = defaultImage.getValue();
        else
            fileImage = getImageIcon(fileName);

        images.put(className, fileImage);
        return fileImage;
    }

    private void setCellSize(int cellSize) {
        if (cellSize < 0)
            throw new IllegalArgumentException("Cell size can't be negative");

        this.cellSize = cellSize;
    }
}
