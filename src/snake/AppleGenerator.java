package snake;

import java.util.ArrayList;
import java.util.Random;

public class AppleGenerator {
    private int applesCount;

    public AppleGenerator(int applesCount) {
        this.applesCount = applesCount;
    }

    public boolean isNeedToAdd(IFieldObject oldCell) {
        return oldCell instanceof Apple;
    }

    public int getApplesCount() {
        return applesCount;
    }

    public void generate(Level level) {
        if (applesCount <= 0)
            return;

        ArrayList<Vector> freeCellsIndexes = getAllFreeCells(level);
        Random random = new Random();
        int index = random.nextInt(freeCellsIndexes.size());
        Vector randomFreeCells = freeCellsIndexes.get(index);
        int x = randomFreeCells.X;
        int y = randomFreeCells.Y;
        level.field[y][x] = new Apple();
        applesCount--;
    }

    private ArrayList<Vector> getAllFreeCells(Level level) {
        ArrayList<Vector> indexesFreeCells = new ArrayList<>();
        for (int i = 0; i < level.field.length; i++){
            for (int j = 0; j < level.field[i].length; j++){
                if(level.field[i][j] instanceof Empty){
                    indexesFreeCells.add(new Vector(j, i));
                }
            }
        }
        return indexesFreeCells;
    }
}
