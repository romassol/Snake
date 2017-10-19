package snakeGUI;

import snake.Level;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class FieldPanel extends JPanel {
    private int cellSize;
    private Level level;
    private HashSet<FieldObjectLabel> labels;

    FieldPanel(Level level, int cellSize) {
        super();
        setBackground(Color.WHITE);

        this.cellSize = cellSize;

        int width = cellSize * level.field[0].length;
        int height = cellSize * level.field.length;

        setPreferredSize(new Dimension(width, height));
        setLayout(new FieldLayout(width, height, cellSize));

        this.level = level;

        labels = new HashSet<>();
        updateLabels();
    }

    public void updateLabels() {
        if (labels.size() > 0)
            removeLabels();

        labels = new HashSet<>();

        for (int y = 0; y < level.field.length; y++) {
            for (int x = 0; x < level.field[y].length; x++) {
                FieldObjectLabel label =
                        new FieldObjectLabel(level.field[y][x], cellSize);
                labels.add(label);
                add(label);
            }
        }
    }

    private void removeLabels() {
        for (FieldObjectLabel label : labels) {
            label.setVisible(false);
            remove(label);
        }
    }
}
