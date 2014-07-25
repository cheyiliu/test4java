
package test.design.pattern.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testFlyWeightDrawWithFlyweight extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Color colors[] = {
            Color.red, Color.blue,
            Color.yellow, Color.orange,
            Color.black, Color.white
    };
    private static final int WINDOW_WIDTH = 400,
            WINDOW_HEIGHT = 400,
            NUMBER_OF_LINES = 100000;

    public testFlyWeightDrawWithFlyweight() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JButton button = new JButton("draw lines");
        final JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(button, BorderLayout.SOUTH);
        setBounds(20, 20, WINDOW_WIDTH, WINDOW_HEIGHT);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                long start = System.currentTimeMillis();
                Graphics g = panel.getGraphics();
                for (int i = 0; i < NUMBER_OF_LINES; ++i) {
                    Line line = LineFactory.getLine(getRandomColor());
                    line.draw(g, getRandomX(), getRandomY(),
                            getRandomX(), getRandomY());
                }
                long end = System.currentTimeMillis();
                System.out.println("draw with flyWeight, time used(ms) =" + (end - start));
            }
        });
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        testFlyWeightDrawWithFlyweight test = new testFlyWeightDrawWithFlyweight();
        test.show();
    }

    private int getRandomX() {
        return (int) (Math.random() * WINDOW_WIDTH);
    }

    private int getRandomY() {
        return (int) (Math.random() * WINDOW_HEIGHT);
    }

    private Color getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    static class Line {
        private Color color;

        public Line(Color color) {
            this.color = color;
        }

        public void draw(Graphics g, int x, int y, int x2, int y2) {
            g.setColor(color);
            g.drawLine(x, y, x2, y2);
        }
    }

    static class LineFactory {
        private static final HashMap<Color, Line> linesByColor = new HashMap<Color, Line>();

        public static Line getLine(Color color) {
            Line line = (Line) linesByColor.get(color);
            if (line == null) {
                line = new Line(color);
                linesByColor.put(color, line);
                System.out.println("Creating " + color + " line");
            }
            return line;
        }
    }
}
