package src.platform;

import src.sounds.Sounds;
import src.level.Level;
import src.player.Player;

import java.awt.*;

public class BoostPlatform extends Platform {
    public BoostPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = new Color(0, 240, 240);
        this.platformSound = new Sounds("src/sounds/boost_sound.wav");
    }

    @Override
    public void applyEffect(Player player) {
        player.setVelocityX(player.getSpeed() * 10);
        platformSound.play();
    }
}
