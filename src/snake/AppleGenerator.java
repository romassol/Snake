package snake;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class AppleGenerator {
    private int applesCount;

    public AppleGenerator(int applesCount) {
        this.applesCount = applesCount;
    }

    public boolean isNeedToAdd(FieldObject oldCell)
    {
        return oldCell instanceof Apple;
    }

    public int getApplesCount() {
        return applesCount;
    }

    public void generate(Level level){
        ArrayList<Vector> indexesFreeCells = getAllFreeCells(level);
        Random random = new Random();
        int index = random.nextInt(indexesFreeCells.size());
        Vector randomFreeCells = indexesFreeCells.get(index);
        int x = randomFreeCells.DELTA_X;
        int y = randomFreeCells.DELTA_Y;
        level.objects[x][y] = new Apple();
        applesCount--;
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
