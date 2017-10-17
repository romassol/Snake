package snakeGUI;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private JLabel scores;
    private JLabel level;

    ScorePanel(String levelName, int scores, int width, int height) {
        super();
        setPreferredSize(new Dimension(width, height));

        this.scores = new JLabel(levelName);
        level = new JLabel(String.valueOf(scores));

        setLayout(new BorderLayout());

        add(this.scores, BorderLayout.WEST);
        add(level, BorderLayout.EAST);
    }
}
