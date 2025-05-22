package src.platform;

import src.level.Level;
import src.player.Player;

import java.awt.*;

public class DeathPlatform extends Platform {
    public DeathPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = Color.RED;
    }
    @Override
    public void applyEffect(Player player) {
        player.resetToCheckpoint();
    }
}
