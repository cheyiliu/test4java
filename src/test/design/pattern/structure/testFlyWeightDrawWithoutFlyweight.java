
package test.design.pattern.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testFlyWeightDrawWithoutFlyweight extends JFrame {
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

    public testFlyWeightDrawWithoutFlyweight() {
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
                    Color color = getRandomColor();
                    System.out.println("Creating " + color + " line");
                    Line line = new Line(color,
                            getRandomX(), getRandomY(),
                            getRandomX(), getRandomY());
                    line.draw(g);
                }
                long end = System.currentTimeMillis();
                System.err.println("draw without flyWeight, time used(ms) =" + (end - start));
            }
        });
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        testFlyWeightDrawWithoutFlyweight test = new testFlyWeightDrawWithoutFlyweight();
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
        private Color color = Color.black;
        private int x, y, x2, y2;

        public Line(Color color, int x, int y, int x2, int y2) {
            this.color = color;
            this.x = x;
            this.y = y;
            this.x2 = x2;
            this.y2 = y2;
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.drawLine(x, y, x2, y2);
        }
    }

}
