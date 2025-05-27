package src.platform;

import src.sounds.Sounds;
import src.level.Level;
import src.player.Player;

import java.awt.*;

public class Platform {
    int x, y, width, height;
    protected Color platformColor;
    protected boolean movingRight = false;
    protected int speed;
    protected int leftBoundary;
    protected int rightBoundary;
    protected int frameCounter = 0;
    protected Sounds platformSound;
    Level level;
    public Platform(int x, int y, int width, int height, Level level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;
        this.platformColor = level.getPlatformColor();
    }

    public void applyEffect(Player player){}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getPlatformColor() { return platformColor; }

    public void move() {
        if (movingRight) {
            frameCounter++;
            if (frameCounter % 3 == 0) { // Jede zweite Aktualisierung bewegen
                x += speed;
            }
            if (x > rightBoundary) {
                movingRight = false;
            }
        } else if (!movingRight) {
            frameCounter++;
            if (frameCounter % 3 == 0) { // Jede zweite Aktualisierung bewegen
                x -= speed;
            }
            if (x < leftBoundary) {
                movingRight = true;
            }
        }
    }
}
