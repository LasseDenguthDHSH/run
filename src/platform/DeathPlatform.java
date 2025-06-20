package src.platform;

import src.sounds.Sounds;
import src.level.Level;
import src.player.Player;

import java.awt.*;

public class DeathPlatform extends Platform {
    public DeathPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = Color.red;
        this.platformSound = new Sounds("src/sounds/error2_sound.wav");
    }

    @Override
    public void applyEffect(Player player) {
        player.resetToCheckpoint(platformSound);
    }
}
