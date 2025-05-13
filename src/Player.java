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
    private Image playerImage;

    public Player(int width, int height, int x, int y, double speed, Image image) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.playerImage = image;
    }

    public Image getImage() {
        return playerImage;
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = -jumpStrength;
        }
    }

    public void applyGravity(int groundY) {
        if (isJumping) {
            y += velocityY;
            velocityY += gravity;

            // Prüfen, ob der Spieler den Boden erreicht hat
            if (y + height >= groundY) {
                y = groundY - height; // Spieler auf den Boden setzen
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