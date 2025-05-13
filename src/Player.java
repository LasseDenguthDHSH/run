package src;

import javax.swing.*;
import java.awt.*;

public class Player {
    int width;
    int height;
    int x;
    int y;
    double speed;
    double gravity = 0.7;
    double jumpStrength = 12;
    double velocityY = 0;
    boolean isJumping = false;
    private ImageIcon playerImage;

    public Player(int width, int height, int x, int y, double speed, String imagePath) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.playerImage = new ImageIcon(imagePath);
    }

    public Image getImage() {
        return playerImage.getImage();
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = -jumpStrength;
        }
    }

    public void applyGravity() {
        if (isJumping) {
            y += velocityY;
            velocityY += gravity;
            if (y >= getGroundLevel()) {
                y = getGroundLevel();
                isJumping = false;
                velocityY = 0;
            }
        }
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    private int getGroundLevel() {
        return y + height;
    }
}