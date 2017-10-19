package snake;

import snakeGUI.ImageSaver;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public class Program {
    public static void main(String[] args) {
        ImageSaver a = new ImageSaver(50);
        Wall wall = new Wall();
        a.getIcon(wall);

        System.out.println(a);
    }
}
