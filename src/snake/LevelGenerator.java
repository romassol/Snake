package snake;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

public class LevelGenerator {

    public LevelGenerator(){
    }

    private Random random = new Random();


    public Level createAndGetLevel(String fileName, int applesCount) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IOException, IllegalAccessException {
        int fieldHeight = random.nextInt(40) + 10;
        int fieldWidth = random.nextInt(40) + 10;
        String[][] field = getStringField(fieldHeight, fieldWidth);
        return new Level(new FieldReader(fileName), applesCount);
    }

    private String[][] getStringField(int fieldHeight, int fieldWidth){
        String[][] field = new String[fieldHeight][fieldWidth];
        List<Cell> cells = new ArrayList<>();
        fillCells(fieldWidth, fieldHeight, cells);
        setInternalCell(cells);
//
        return field;
    }

    private void fillCells(int fieldWidth, int fieldHeight, List<Cell> cells){
        int cellSize = random.nextInt(min(fieldWidth, fieldHeight)) + 5;
        int numberOfCellsVertically = fieldHeight / cellSize;
        for (int i = 0; i < numberOfCellsVertically; i++){
            fillHorizontal(i, cellSize, cellSize, fieldWidth, cells);
        }
    }

    private void fillHorizontal(int numberOfRow, int heightOfCell, int widthOfCell, int fieldWidth, List<Cell> cells) {
        int numberOfCellsHorizontally = fieldWidth / widthOfCell;
        for (int j = 0; j < numberOfCellsHorizontally; j++){
            Vector topLeft = new Vector(j*widthOfCell, numberOfRow*widthOfCell);
            Vector bottomRight = topLeft.sum(new Vector(widthOfCell - 1, heightOfCell - 1));
            cells.add(new Cell(topLeft, bottomRight, null));
        }
    }

    private void setInternalCell(List<Cell> cells){
        for (int i = 0; i < cells.size(); i++){
            if (cells.get(i).height >= 4 && cells.get(i).width >= 4) {
                boolean room = random.nextBoolean();
                if(room) {
                    int horizontallyShift = random.nextInt(cells.get(i).width - 4);
                    int verticallyShift = random.nextInt(cells.get(i).height - 4);
                    Vector shift = new Vector(-horizontallyShift, -verticallyShift);
                    cells.get(i).internalCell.setTopLeft(cells.get(i).getTopLeft());
                    cells.get(i).internalCell.setBottomRight(cells.get(i).getBottomRight().sum(shift));
                }
            }
        }
    }

    public static void main(String[] args){

    }
}
