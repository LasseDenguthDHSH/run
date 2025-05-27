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
}