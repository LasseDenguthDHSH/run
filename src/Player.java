package src;

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
    boolean isOnPlatform = false;
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

    public void applyGravity(Level level) {
        for (Platform platform : level.getPlatforms()) {
            if (x <= platform.x || x > platform.x + platform.width) {
                isOnPlatform = false;
            }
        }
        if (isJumping || !isOnPlatform) {
            y += velocityY;
            velocityY += gravity;
            if (y + height >= level.getGroundY()) {
                y = level.getGroundY() - height;
                isJumping = false;
                velocityY = 0;
            }
            for (Platform platform : level.getPlatforms()) {
                if (y + height >= platform.y && y + height - velocityY < platform.y && x + width > platform.x && x < platform.x + platform.width) {
                    y = platform.y - height; // Spieler auf Plattform setzen
                    isJumping = false;
                    velocityY = 0;
                    isOnPlatform = true;
                    return;
                }
            }
        }
        // Prüfen, ob der Spieler auf einer Plattform landet

        // Prüfen, ob der Spieler auf dem Boden landet


    }

    public void moveLeft() {
        if (x >= 0) {
            x -= speed;
        }
    }

    public void moveRight() {
        x += speed;
    }

    private int getGroundLevel() {
        return y + height;
    }
}