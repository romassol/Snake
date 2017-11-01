package snakeGUI;

import snake.IFieldObject;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimationObject {
    protected String[] fileImages;

    public abstract String getImageFileName(IFieldObject obj);

    public AnimationObject(String[] fileImages) {
        this.fileImages = fileImages;
    }
}
