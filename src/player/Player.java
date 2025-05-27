package src.player;

import src.Sounds;
import src.platform.*;
import src.level.*;

import java.awt.*;

public class Player {
    int width;
    int height;
    int x;
    int y;
    double speed;
    double jumpStrength = 11;
    double velocityY = 0;
    boolean isJumping = false;
    boolean isOnPlatform = false;
    boolean isOnGround = true;
    private Image playerImage;
    private double velocityX = 0;
    private int checkpointX;
    private int checkpointY;
    private Sounds jumpingSound;
    private Sounds respawnSound;


    public Player(int width, int height, Level level, Image image) {
        this.width = width;
        this.height = height;
        this.x = level.getPlayerStartX();
        this.y = level.getPlayerStartY();
        this.speed = level.getPlayerSpeed();
        this.playerImage = image;
        this.jumpingSound = new Sounds("src/sounds/jump.wav");
        jumpingSound.setVolume(1);
        this.respawnSound = level.getRespawnSound();
    }

    public Image getImage() {
        return playerImage;
    }

    public void jump() {
        if (!isJumping && isOnPlatform || isOnGround) {
            jumpingSound.play();
            isOnGround = false;
            isOnPlatform = false;
            isJumping = true;
            velocityY = -jumpStrength;
        }
    }

    public void applyGravity(Level level) { //Spieler runter von Platform
        for (Platform platform : level.getPlatforms()) {
            if (x <= platform.getX() || x > platform.getX() + platform.getWidth()) {
                isOnPlatform = false;
            }
        }
        if (isJumping || !isOnPlatform) {
            y += velocityY;
            velocityY += level.getGravity();
            if (y + height >= level.getGroundY()) {
                y = level.getGroundY() - height;
                isJumping = false;
                isOnGround = true;
                resetToCheckpoint(level, respawnSound);
                velocityY = 0;
            }
            //Spieler aufPlatform
            for (Platform platform : level.getPlatforms()) {
                if (y + height >= platform.getY() && y + height - velocityY < platform.getY() && x + width - 10 > platform.getX() && x < platform.getX() + platform.getWidth()) {
                    y = platform.getY() - height;
                    isJumping = false;
                    velocityY = 0;
                    isOnPlatform = true;
                    platform.applyEffect(this);

                    // Speichert die letzte sichere Position
                    if (level instanceof Level1 && !(platform instanceof CheckpointPlatform) && !(platform instanceof DeathPlatform) && !(platform instanceof JumpPlatform) && !(platform instanceof BoostPlatform) && !(platform instanceof MovingPlatform)) {
                        checkpointX = platform.getX() + platform.getWidth() / 2 - 16;
                        checkpointY = platform.getY() - 32;
                    } else if (level instanceof Level3) {
                        checkpointX = level.getPlayerStartX();
                        checkpointY = level.getPlayerStartY();
                    }
                    return;
                }
            }
        }

}

    public void boostMovement() {
        x += velocityX;
        velocityX *= 0.95;

        if (Math.abs(velocityX) < 0.2) {
            velocityX = 0;
        }
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }


    public void moveLeft(double speed) {
        if (x >= 0) {
            x -= speed;
        }
    }

    public void moveRight(double speed) {
        x += speed;
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

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getSpeed() {
        return speed;
    }
    public void setCheckpoint(int x, int y) {
        this.checkpointX = x;
        this.checkpointY = y;
    }
    public void resetToCheckpoint(Level level, Sounds respawnSound) {
        this.x = checkpointX;
        this.y = checkpointY;
        respawnSound.play();
        this.velocityX = 0;
    }


}