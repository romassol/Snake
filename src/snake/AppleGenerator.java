package snake;

import java.util.Objects;

public class AppleGenerator {
    private Integer applesCount;

    public AppleGenerator(Integer applesCount) {
        this.applesCount = applesCount;
    }

    public boolean isNeedToAdd(FieldObject oldCell) {
        return Objects.equals(oldCell.getClass().getName(), "Apple");
    }

    public void generate(Level level){
        
        applesCount--;
    }
}
