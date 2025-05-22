package src.platform;

import src.level.Level;

import java.awt.*;

public class BewegungPlatform extends Platform {
    private int speed;
    private boolean movingRight;
    private int leftBoundary;
    private int rightBoundary;

    public BewegungPlatform(int x, int y, int width, int height, Level level, int speed, boolean movingRight, int leftBoundary, int rightBoundary) {
        super(x, y, width, height, level);
        this.speed = speed;
        this.movingRight = movingRight;
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        this.platformColor = new Color(100, 100, 100);
    }

    public void move() {
        if (movingRight) {
            x += speed;
            if (x > rightBoundary - width) {
                movingRight = false;
            }
        } else {
            x -= speed;
            if (x < leftBoundary) {
                movingRight = true;
            }
        }
    }
}
