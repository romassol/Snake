package snakeGUI;

import java.awt.*;

public class FieldLayout implements LayoutManager {
    private int width;
    private int height;

    FieldLayout(int width, int height) {
        super();
        this.width = width;
        this.height = height;
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
        int x = 0;
        int y = 0;
        for (Component component : parent.getComponents()) {
            Dimension pref = component.getPreferredSize();
            component.setBounds(x, y, pref.width, pref.height);
            x = (x + pref.width) % width;
            if (x == 0)
                y += pref.height;
        }
    }
}
