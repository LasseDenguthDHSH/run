package src.player;

import src.platform.Platform;
import src.level.Level;
import src.platform.SprungPlatform;

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
    boolean isOnGround = true;
    private Image playerImage;

    public Player(int width, int height, Level level, double speed, Image image) {
        this.width = width;
        this.height = height;
        this.x = level.getPlayerStartX();
        this.y = level.getPlayerStartY();
        this.speed = speed;
        this.playerImage = image;
    }

    public Image getImage() {
        return playerImage;
    }

    public void jump() {
        if (!isJumping && isOnPlatform || isOnGround) {
            isOnGround = false;
            isOnPlatform = false;
            isJumping = true;
            velocityY = -jumpStrength;
        }
    }

    public void applyGravity(Level level) {
        for (Platform platform : level.getPlatforms()) {
            if (x <= platform.getX() || x > platform.getX() + platform.getWidth()) {
                isOnPlatform = false;
            }
        }
        if (isJumping || !isOnPlatform) {
            y += velocityY;
            velocityY += gravity;
            if (y + height >= level.getGroundY()) {
                y = level.getGroundY() - height;
                isJumping = false;
                isOnGround = true;
                velocityY = 0;
            }
            for (Platform platform : level.getPlatforms()) {
                if (y + height >= platform.getY() && y + height - velocityY < platform.getY() && x + width > platform.getX() && x < platform.getX() + platform.getWidth()) {
                    y = platform.getY() - height; // Spieler auf Plattform setzen
                    isJumping = false;
                    velocityY = 0;
                    isOnPlatform = true;
                    platform.applyEffect(this);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getJumpStrength() {
        return jumpStrength;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setJumpStrength(double jumpStrength) {
        this.jumpStrength = jumpStrength;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}