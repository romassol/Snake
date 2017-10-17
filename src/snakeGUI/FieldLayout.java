package snakeGUI;

import java.awt.*;

public class FieldLayout implements LayoutManager {
    private int width;
    private int height;
    private int cellSize;

    FieldLayout(int width, int height, int cellSize) {
        super();
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) { }

    @Override
    public void removeLayoutComponent(Component comp) { }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(width, height);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(width, height);
    }

    @Override
    public void layoutContainer(Container parent) {
        Component list[] = parent.getComponents();
        int x = 0;
        int y = 0;
        for (Component component : list) {
            Dimension pref = component.getPreferredSize();
            component.setBounds(x, y, pref.width, pref.height);
            x = (x + pref.width) % width;
            if (x == 0)
                y += pref.height;
        }
    }
}
