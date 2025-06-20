package src.platform;

import src.sounds.Sounds;
import src.level.Level;
import src.player.Player;

import java.awt.*;

public class Platform {
    protected int x, y, width, height;
    protected int speed;
    protected int leftBoundary;
    protected int rightBoundary;
    protected int frameCounter = 0;
    protected boolean movingRight = false;
    protected Color platformColor;
    protected Sounds platformSound;

    public Platform(int x, int y, int width, int height, Level level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.platformColor = level.getPlatformColor();
    }

    public void applyEffect(Player player) {
    }

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

    public Color getPlatformColor() {
        return platformColor;
    }
}
