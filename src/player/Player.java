package src.player;

import src.level.Level1;
import src.level.Level2;
import src.level.Level3;
import src.platform.BoostPlatform;
import src.platform.DeathPlatform;
import src.platform.Platform;
import src.level.Level;
import src.platform.SprungPlatform;

import java.awt.*;

public class Player {
    int width;
    int height;
    int x;
    int y;
    int speed;
    double gravity = 0.7;
    double jumpStrength = 12;
    double velocityY = 0;
    boolean isJumping = false;
    boolean isOnPlatform = false;
    boolean isOnGround = true;
    private Image playerImage;
    private int lastX;
    private int lastY;
    private double velocityX = 0;

    public Player(int width, int height, Level level, int speed, Image image) {
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
                // Checkpoint
                if (x>level.getPlayerStartX()+100 && level instanceof Level1) {
                    setPosition(getLastX(), getLastY());
                } else if(x>level.getPlayerStartX() + 100 && level instanceof Level2) {

                } else if (x>level.getPlayerStartX() + 100 && level instanceof Level3) {
                    setPosition(level.getPlayerStartX(), level.getPlayerStartY());
                }
                velocityY = 0;
            }
            for (Platform platform : level.getPlatforms()) {
                if (y + height >= platform.getY() && y + height - velocityY < platform.getY() &&
                        x + width > platform.getX() && x < platform.getX() + platform.getWidth()) {

                    y = platform.getY() - height;
                    isJumping = false;
                    velocityY = 0;
                    isOnPlatform = true;
                    platform.applyEffect(this);

                    // Speichert die letzte sichere Position
                    if (!(platform instanceof SprungPlatform) && !(platform instanceof DeathPlatform) && !(platform instanceof BoostPlatform)) {
                        lastX = platform.getX()+platform.getWidth()/2-32;
                        lastY = platform.getY()-32;
                    }

                    return;
                }
            }
        }
        // Prüfen, ob der Spieler auf einer Plattform landet

        // Prüfen, ob der Spieler auf dem Boden landet
    }

    public void boostMovement() {
        x += velocityX; // Bewegung nach rechts
        velocityX *= 0.95; // Langsames Abbremsen

        if (Math.abs(velocityX) < 0.2) {
            velocityX = 0; // Stoppt die Bewegung, wenn sie zu langsam wird
        }
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }


    public void moveLeft(int speed) {
        if (x >= 0) {
            x -= speed;
        }
    }

    public void moveRight(int speed) {
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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isOnPlatform() {
        return isOnPlatform;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public int getSpeed() {
        return speed;
    }
}