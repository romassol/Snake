package snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

public class FieldGenerator {

    public FieldGenerator(){
        fillCellBorders();
        moveBottomRightBorders();
    }

    private Random random = new Random();
    public int fieldHeight = random.nextInt(40) + 10;
    public int fieldWidth = random.nextInt(40) + 10;
    private int cellSize = random.nextInt(min(fieldWidth, fieldHeight) - 5) + 5;
    private List<Cell> cells = new ArrayList<>();
    private int numberOfCellsHorizontally = fieldWidth / cellSize;
    private int remainingHorizontally = fieldWidth % cellSize;
    private int numberOfCellsVertically = fieldHeight / cellSize;
    private int remainingVertically = fieldHeight % cellSize;


    private void fillCellBorders(){
        for (int i = 0; i < numberOfCellsVertically; i++){
            fillHorizontal(i, cellSize);
        }
        if (remainingVertically != 0){
            fillHorizontal(numberOfCellsVertically, remainingVertically);
        }
    }

    private void fillHorizontal(int numberOfRow, int heightOfCell) {
        for (int j = 0; j < numberOfCellsHorizontally; j++){
            Vector topLeft = new Vector(j*cellSize, numberOfRow*cellSize);
            Vector bottomRight = topLeft.sum(new Vector(cellSize - 1, heightOfCell - 1));
            cells.add(new Cell(topLeft, bottomRight));
        }
        if (remainingHorizontally != 0){
            Vector topLeft = new Vector(fieldWidth - remainingHorizontally, numberOfRow * cellSize);
            Vector bottomRight = topLeft.sum(new Vector(remainingHorizontally - 1, heightOfCell - 1));
            cells.add(new Cell(topLeft, bottomRight));
        }
    }

    private void moveBottomRightBorders(){
        for (int i = 0; i < cells.size(); i++){
            if (cells.get(i).height >= 4 && cells.get(i).width >= 4) {
                int horizontallyShift = random.nextInt(cells.get(i).width - 4);
                int verticallyShift = random.nextInt(cells.get(i).height - 4);
                Vector shift = new Vector(-horizontallyShift, -verticallyShift);
                cells.get(i).setBottomRight(cells.get(i).getBottomRight().sum(shift));
            }
        }
    }

    private void addPasses(){

    }



    public static void main(String[] args){
        FieldGenerator f = new FieldGenerator();
        System.out.println(f.cells);
    }
}
