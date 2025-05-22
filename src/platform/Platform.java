package src.platform;

import src.level.Level;
import src.player.Player;

import java.awt.*;

public class Platform {
    int x, y, width, height;
    protected Color platformColor;

    public Platform(int x, int y, int width, int height, Level level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
}
