package snakeGUI;

import snake.Level;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

class FieldPanel extends JPanel {
    private int cellSize;
    private Level level;
    private HashSet<FieldObjectImage> labels;
    private ImageSaver imageSaver;

    FieldPanel(Level level, int cellSize) {
        super();
        setBackground(Color.WHITE);

        this.cellSize = cellSize;
        imageSaver = new ImageSaver(cellSize);

        int width = cellSize * level.field[0].length;
        int height = cellSize * level.field.length;

        setPreferredSize(new Dimension(width, height));
        setLayout(new FieldLayout(width, height, cellSize));

        this.level = level;

        labels = new HashSet<>();
        updateLabels();
    }

    void updateLabels() {
        if (labels.size() > 0)
            removeLabels();

        labels = new HashSet<>();

        for (int y = 0; y < level.field.length; y++) {
            for (int x = 0; x < level.field[y].length; x++) {
                FieldObjectImage label = new FieldObjectImage(
                        level.field[y][x],
                        cellSize,
                        imageSaver);
                labels.add(label);
                add(label);
            }
        }
    }

    private void removeLabels() {
        for (FieldObjectImage label : labels) {
            label.setVisible(false);
            remove(label);
        }
    }
}
