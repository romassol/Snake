package snakeGUI;

import snake.IFieldObject;

import java.util.HashMap;

public class ElementAnimationObject extends AnimationObject {
    private HashMap<IFieldObject, Integer> indexes;

    public ElementAnimationObject(String[] fileImages) {
        super(fileImages);
        indexes = new HashMap<>();
    }

    @Override
    public String getImageFileName(IFieldObject obj) {
        if (!indexes.containsKey(obj))
            indexes.put(obj, 0);

        Integer result = indexes.get(obj);
        Integer nextValue = (result + 1) % fileImages.length;
        indexes.put(obj, nextValue);

        return fileImages[result];
    }
}
