package src.player;

import src.sounds.Sounds;
import src.platform.*;
import src.level.*;

import java.awt.*;

public class Player {
    String name;
    int width;
    int height;
    int x;
    int y;
    double speed;
    double jumpStrength;
    double velocityY = 0;
    boolean isJumping = false;
    boolean isOnPlatform = false;
    boolean isOnGround = true;
    private Image playerImage;
    private double velocityX = 0;
    private int checkpointX;
    private int checkpointY;
    private Sounds respawnSound;
    private Level currentLevel;


    public Player(String name, int width, int height, Level currentLevel, Image image) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.currentLevel = currentLevel;
        this.x = currentLevel.getPlayerStartX();
        this.y = currentLevel.getPlayerStartY();
        this.speed = currentLevel.getPlayerSpeed();
        this.jumpStrength = currentLevel.getPlayerJumpStrength();
        this.playerImage = image;
        this.respawnSound = currentLevel.getRespawnSound();
    }

    public Image getImage() {
        return playerImage;
    }

    public void jump() {
        if (currentLevel instanceof ChickenLevel){
                currentLevel.getJumpSound().play();
                isOnGround = false;
                isOnPlatform = false;
                isJumping = true;
                velocityY = -jumpStrength;
        }else{
            if (!isJumping && isOnPlatform || isOnGround) {
                currentLevel.getJumpSound().play();
                isOnGround = false;
                isOnPlatform = false;
                isJumping = true;
                velocityY = -jumpStrength;
            }
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
                if (!(level instanceof ChickenLevel)){
                    resetToCheckpoint(level, respawnSound);
                }
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
                    if (level instanceof Level1 && !(platform instanceof CheckpointPlatform) && !(platform instanceof DeathPlatform) && !(platform instanceof JumpPlatform) && !(platform instanceof BoostPlatform) && !(platform instanceof MovingPlatform) && !(platform instanceof EntityPlatform)) {
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

    public String getName() {
        return name;
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