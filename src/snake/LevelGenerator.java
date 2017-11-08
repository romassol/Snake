package snake;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

public class LevelGenerator {

    private Random random = new Random();


    public Level createAndGetLevel(String fileName, int applesCount)
            throws InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IOException,
            IllegalAccessException {
        int fieldHeight = random.nextInt(40) + 10;
        int fieldWidth = random.nextInt(40) + 10;
        String[][] field = getStringField(fieldHeight, fieldWidth);
        createLevelFile(fileName, field);
        return new Level(new FieldReader(fileName), applesCount);
    }

    private void createLevelFile(String fileName, String[][] field) throws IOException {
        File levelFile = new File(Settings.LEVEL_URL +fileName);
        FileWriter writeFile = new FileWriter(levelFile);
        String strField = "";
        for (String[] line : field){
            strField += String.join("", line);
            strField += '\n';
        }
        writeFile.append(strField);
    }

    private String[][] getStringField(int fieldHeight, int fieldWidth){
        String[][] field = new String[fieldHeight][fieldWidth];
        List<Cell> cells = new ArrayList<>();
        int cellSize = random.nextInt(min(fieldWidth, fieldHeight)) + 5;
        fillCells(fieldWidth, fieldHeight, cellSize, cells);
        setInternalCell(cells);
        fillField(field, cells);
        makeHoles(field, cellSize, fieldHeight, fieldWidth);
        return field;
    }

    private void fillField(String[][] field, List<Cell> cells){
        for (Cell cell : cells){
            Cell room = cell.internalCell;
            for (int x = room.getTopLeft().x; x <= room.getBottomRight().x; x++){
                field[x][room.getTopLeft().y] = "#";
                field[x][room.getBottomRight().y] = "#";
            }
            for (int y = room.getTopLeft().y; y < room.getBottomRight().y; y++){
                field[room.getTopLeft().x][y] = "#";
                field[room.getBottomRight().x][y] = "#";
            }
        }
    }

    private void makeHoles(String[][] field, int cellsize, int fieldHeight, int fieldWidth){
        // режем дырки горизонтально
        for (int x = 0; x < fieldHeight / cellsize; x++){
            int holeMark = random.nextInt(cellsize) + 1 + cellsize * x;
            int xLayer = x * cellsize + holeMark;
            for (int y = 1; y < fieldWidth - 1; y++){
                field[xLayer][y] = " ";
            }
        }
        // режем дырки вертикально
        for (int y = 0; y < fieldWidth / cellsize; y++){
            int holeMark = random.nextInt(cellsize) + 1 + cellsize * y;
            int yLayer = y * cellsize + holeMark;
            for (int x = 1; x < fieldHeight - 1; x++){
                field[x][yLayer] = " ";
            }
        }
    }

    private void fillCells(int fieldWidth, int fieldHeight, int cellSize, List<Cell> cells){
        int numberOfCellsVertically = fieldHeight / cellSize;
        int remainder = fieldHeight % cellSize;
        for (int i = 0; i < numberOfCellsVertically; i++){
            if (i == numberOfCellsVertically - 1)
                fillHorizontal(i, cellSize + remainder, cellSize, fieldWidth, cells);
            else
                fillHorizontal(i, cellSize, cellSize, fieldWidth, cells);
        }
    }

    private void fillHorizontal(int numberOfRow, int heightOfCell, int widthOfCell, int fieldWidth, List<Cell> cells) {
        int numberOfCellsHorizontally = fieldWidth / widthOfCell;
        int remainder = fieldWidth % widthOfCell;
        for (int j = 0; j < numberOfCellsHorizontally; j++){
            Vector topLeft = new Vector(j*widthOfCell, numberOfRow*widthOfCell);
            Vector bottomRight = topLeft.sum(new Vector(widthOfCell - 1, heightOfCell - 1));
            if (j == numberOfCellsHorizontally - 1)
                bottomRight = topLeft.sum(new Vector(widthOfCell + remainder - 1, heightOfCell - 1));
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
}
