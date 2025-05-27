package src.platform;

import src.Music;
import src.Sounds;
import src.level.Level;
import src.player.Player;

import java.awt.*;

public class DeathPlatform extends Platform {
    public DeathPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = Color.red;
        this.platformSound = new Sounds("src/sounds/error_sound2.wav");
    }
    @Override
    public void applyEffect(Player player) {
        player.resetToCheckpoint(level, platformSound);
    }
}
