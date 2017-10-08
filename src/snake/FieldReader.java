package snake;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class FieldReader {
    public String fileName;
    private Map<String, Class> characterSymbol;
    public FieldObject[][] objects;

    public FieldReader(String fileName) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException,
            InvocationTargetException, IOException {
        this.fileName = fileName;
        characterSymbol = new HashMap<>();
        characterSymbol.put("#", Wall.class);
        characterSymbol.put(" ", Empty.class);
        characterSymbol.put("A", Apple.class);
        characterSymbol.put("S", SnakePart.class);
        fillObjects();
    }

    public void fillObjects() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException, IOException {
        System.out.println(Paths.get(fileName));
        List<String> lines = Files.readAllLines(
                Paths.get(fileName),
                StandardCharsets.UTF_8);
        try {
            objects = new FieldObject[lines.size()][lines.get(0).length()];
        } catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Field is incorrect");
        }
        for(int i = 0; i<lines.size(); i++){
            for (int j = 0; j < lines.get(i).length(); j++){
                String symbol = String.valueOf(lines.get(i).charAt(j));
                if (Objects.equals(symbol, "S")){
                    Class[] type = new Class[4];
                    Integer[] arg = new Integer[4];
                    type[0] = Integer.class;
                    type[1] = Integer.class;
                    type[2] = Direction.class;
                    type[3] = SnakePart.class;
                    arg[0] = j;
                    arg[1] = i;
                    arg[2] = null;
                    arg[3] = null;
                    objects[i][j] = (FieldObject) characterSymbol
                            .get(symbol)
                            .getConstructor(type)
                            .newInstance(arg[0], arg[1], arg[2], arg[3]);
                }
                else {
                    Class[] type = new Class[2];
                    Integer[] arg = new Integer[2];
                    type[0] = Integer.class;
                    type[1] = Integer.class;
                    arg[0] = j;
                    arg[1] = i;
                    objects[i][j] = (FieldObject) characterSymbol
                            .get(symbol).getConstructor(type)
                            .newInstance(arg[0], arg[1]);
                }
            }
        }
    }
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        FieldReader r = new FieldReader("level1.txt");
        for(int i = 0; i<r.objects.length; i++) {
            for (int j = 0; j < r.objects[i].length; j++) {
                System.out.println(r.objects[i][j]);
                System.out.println(r.objects[i][j].x);
                System.out.println(r.objects[i][j].y);
                if (r.objects[i][j] instanceof SnakePart){
                    System.out.println(((SnakePart) r.objects[i][j]).direction);
                    System.out.println(((SnakePart) r.objects[i][j]).parent);
                }
                System.out.println();
            }
        }
    }
}
