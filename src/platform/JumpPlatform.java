package src.platform;

import src.Sounds;
import src.level.Level;
import src.player.Player;

import java.awt.*;

public class JumpPlatform extends Platform {
    double jumpPlatformEffect;
    public JumpPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = new Color(200, 100, 0);
        this.jumpPlatformEffect= level.getJumpPlatformEffect();
        this.platformSound = new Sounds("src/sounds/jump.wav");
    }

    @Override
    public void applyEffect(Player player) {

        player.setVelocityY(jumpPlatformEffect);
        platformSound.play();
    }
}
