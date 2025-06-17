package src.platform;

import src.level.Level;

import java.awt.*;

public class MovingPlatform extends Platform {


    public MovingPlatform(int x, int y, int width, int height, Level level, int speed, boolean movingRight, int leftBoundary, int rightBoundary) {
        super(x, y, width, height, level);
        this.speed = speed;
        this.movingRight = movingRight;
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        this.platformColor = level.getPlatformColor();
    }

    public void move(int platformSpeed) {
        if (movingRight) {
            frameCounter++;
            if (frameCounter % platformSpeed == 0) { // Jede zweite Aktualisierung bewegen
                x += speed;
            }
            if (x > rightBoundary) {
                movingRight = false;
            }
        } else {
            frameCounter++;
            if (frameCounter % platformSpeed == 0) { // Jede zweite Aktualisierung bewegen
                x -= speed;
            }
            if (x < leftBoundary) {
                movingRight = true;
            }
        }
    }
}