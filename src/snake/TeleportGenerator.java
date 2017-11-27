package snake;

import java.util.ArrayList;
import java.util.Random;

public class TeleportGenerator {
    private int teleportsPairCount;

    public TeleportGenerator(int teleportsPairCount) {
        this.teleportsPairCount = teleportsPairCount;
    }

    public boolean isNeedToAdd(IFieldObject oldCell) {
        return oldCell instanceof Teleport;
    }

    public void generate(Level level) {
        if (teleportsPairCount <= 0)
            return;

        ArrayList<Vector> freeCellsIndexes = getAllFreeCells(level);
        Random random = new Random();

        int firstIndex = random.nextInt(freeCellsIndexes.size());
        Vector randomFirstFreeCell = freeCellsIndexes.get(firstIndex);

        freeCellsIndexes.remove(firstIndex);
        int secondIndex = random.nextInt(freeCellsIndexes.size());
        Vector randomSecondFreeCell = freeCellsIndexes.get(secondIndex);

        level.setObjectOnField(randomFirstFreeCell, new Teleport());
        level.setObjectOnField(randomSecondFreeCell, new Teleport());
        level.getTeleports().put((Teleport) level.getFieldObject(randomFirstFreeCell.x, randomFirstFreeCell.y),randomFirstFreeCell);
        level.getTeleports().put((Teleport) level.getFieldObject(randomSecondFreeCell.x, randomSecondFreeCell.y),randomSecondFreeCell);

        teleportsPairCount--;
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
