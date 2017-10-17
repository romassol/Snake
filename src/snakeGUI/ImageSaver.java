package snakeGUI;

import javafx.util.Pair;
import snake.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Hashtable;

public final class ImageSaver {
    private static Hashtable<String, ImageIcon> images =
            new Hashtable<>();
    private static Pair<String, ImageIcon> defaultImage;

    public static void loadImages(int size) {
        defaultImage = new Pair<>(
                Settings.imageUrl + "default.png",
                getImageIcon(Settings.imageUrl + "default.png", size));

        Hashtable<Class, String> names = new Hashtable<>();
        names.put(Empty.class, "empty.jpg");
        names.put(Wall.class, "wall.jpg");
        names.put(Apple.class, "apple.jpg");
        names.put(SnakePart.class, "snakepart.jpg");

        names.forEach((key, value) -> {
            String name = key.getName().toLowerCase();

            String fileName = Settings.imageUrl + value;

            if (!new File(fileName).exists()) {
                System.out.println("Can't find a " + fileName + "!");
                fileName = defaultImage.getKey();
            }

            images.put(name, getImageIcon(fileName, size));
        });
    }

    private static ImageIcon getImageIcon(String fileName, int size) {
        Image img = new ImageIcon(fileName)
                .getImage()
                .getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon();
        imageIcon.setImage(img);

        return imageIcon;
    }

    private static String getImageName(FieldObject obj) {
        return obj.getClass().getName().toLowerCase() + ".png";
    }

    public static ImageIcon getIcon(FieldObject obj) {
        String className = obj.getClass().getName().toLowerCase();

        if (images.containsKey(className))
            return images.get(className);
        return defaultImage.getValue();
    }
}
