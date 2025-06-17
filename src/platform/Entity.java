package src.platform;

import javax.swing.*;
import java.awt.*;

public class Entity {
    int speed;
    int rightBoundary;
    int leftBoundary;
    int x;
    int y;
    boolean movingRight;
    int frameCounter = 0;
    int size = 32;

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
