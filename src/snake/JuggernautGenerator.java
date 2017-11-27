package snake;

import java.util.ArrayList;
import java.util.Random;

public class JuggernautGenerator {
    private int juggernautesCount;
    public int timer;

    public JuggernautGenerator(int juggernautesCount) {
        this.juggernautesCount = juggernautesCount;
    }

    public void changeTimer(int value){
        this.timer += value;
    }

    public boolean isNeedToAdd() {
        return (timer - 15000) > 0 && juggernautesCount > 0;
    }

    public void generate(Level level) {
        if (juggernautesCount <= 0)
            return;

        ArrayList<Vector> freeCellsIndexes;
        freeCellsIndexes = getAllFreeCells(level);
        Random random = new Random();
        int index = random.nextInt(freeCellsIndexes.size());
        Vector randomFreeCell = freeCellsIndexes.get(index);
        level.setObjectOnField(randomFreeCell, new Juggernaut());
        juggernautesCount--;
        this.timer -= 15000;
    }

    private ArrayList<Vector> getAllFreeCells(Level level) {
        ArrayList<Vector> indexesFreeCells = new ArrayList<>();
        Vector levelSize = level.getLevelSize();
        for (int y = 0; y < levelSize.y; y++) {
            for (int x = 0; x < levelSize.x; x++) {
                if(level.getFieldObject(x, y) instanceof Empty) {
                    indexesFreeCells.add(new Vector(x, y));
                }
            }
        }
        return indexesFreeCells;
    }
}
