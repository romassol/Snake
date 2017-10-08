package snake;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class AppleGenerator {
    private Integer applesCount;

    public AppleGenerator(Integer applesCount) {
        this.applesCount = applesCount;
    }

    public boolean isNeedToAdd(FieldObject oldCell) {
        return Objects.equals(oldCell.getClass().getName(), "Apple");
    }

    public void generate(Level level){
        while(applesCount > 0){
            ArrayList<Vector> indexesFreeCells = getAllFreeCells(level);
            Random random = new Random();
            Integer index = random.nextInt(indexesFreeCells.size());
            Vector randomFreeCells = indexesFreeCells.get(index);
            Integer x = randomFreeCells.DELTA_X;
            Integer y = randomFreeCells.DELTA_Y;
            level.objects[x][y] = new Apple(x, y);
            applesCount--;
        }

    }

    private ArrayList<Vector> getAllFreeCells(Level level) {
        ArrayList<Vector> indexesFreeCells = new ArrayList<>();
        for (int i = 0; i < level.objects.length; i++){
            for (int j = 0; j < level.objects[i].length; j++){
                if(level.objects[i][j] instanceof Empty){
                    indexesFreeCells.add(new Vector(j, i));
                }
            }
        }
        return indexesFreeCells;
    }
}
