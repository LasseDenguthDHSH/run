package src.platform;

import src.player.Player;

import java.awt.*;

public class DeathPlatform extends Platform {
    public DeathPlatform(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.platformColor = Color.RED;
    }
    @Override
    public void applyEffect(Player player) {
        player.setPosition(player.getLastX(), player.getLastY());
    }
}
