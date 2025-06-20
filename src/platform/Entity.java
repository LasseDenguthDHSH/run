package src.platform;

import javax.swing.*;
import java.awt.*;

public class Entity {
    protected int speed;
    protected int rightBoundary;
    protected int leftBoundary;
    protected int x;
    protected int y;
    protected int frameCounter = 0;
    protected int size = 32;
    protected boolean movingRight;

    Image entityImage;

    public Entity(Platform platform, int speed) {
        this.speed = speed;
        this.x = platform.getX();
        this.y = platform.getY() - 32;
        this.leftBoundary = platform.getX();
        this.rightBoundary = platform.getX() + platform.getWidth() - size;
        this.entityImage = new ImageIcon("src/images/entity.png").getImage();
        this.movingRight = true;
    }

    public void move(int platformSpeed) {
        if (movingRight) {
            frameCounter++;
            if (frameCounter % platformSpeed == 0) {
                x += speed;
            }
            if (x > rightBoundary) {
                movingRight = false;
            }
        } else {
            frameCounter++;
            if (frameCounter % platformSpeed == 0) {
                x -= speed;
            }
            if (x < leftBoundary) {
                movingRight = true;
            }
        }
    }

    public Image getEntityImage() {
        return entityImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
