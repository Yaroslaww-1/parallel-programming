package Part2ColoredBalls;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private static final int POCKET_X_SIZE = 20;
    private static final int POCKET_Y_SIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    public boolean isRed;
    public int id;

    public Ball(Component c, boolean isRed) {
        canvas = c;
        this.isRed = isRed;
        id = new Random().nextInt(10000000);
        x = 100;
        y = 100;
    }

    public static void f() {
        int a = 0;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(isRed ? Color.red : Color.blue);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        } if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    public boolean isInPocket() {
        var middleX = x + XSIZE / 2;
        var middleY = y + YSIZE / 2;

        var isInTopLeftPocket = middleX < POCKET_X_SIZE && middleY < POCKET_Y_SIZE;
        var isInBottomLeftPocket = middleX < POCKET_X_SIZE && (canvas.getHeight() - middleY) < POCKET_Y_SIZE;
        var isInTopRightPocket = (canvas.getWidth() - middleX) < POCKET_X_SIZE && middleY < POCKET_Y_SIZE;
        var isInBottomRightPocket = (canvas.getWidth() - middleX) < POCKET_X_SIZE && (canvas.getHeight() - middleY) < POCKET_Y_SIZE;

        return isInTopLeftPocket || isInBottomLeftPocket || isInTopRightPocket || isInBottomRightPocket;
    }
}