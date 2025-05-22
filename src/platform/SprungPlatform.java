package src.platform;

import src.level.Level;
import src.player.Player;

import java.awt.*;

public class SprungPlatform extends Platform {
    double jumpPlatformEffect;
    public SprungPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = new Color(200, 100, 0);
        this.jumpPlatformEffect= level.getJumpPlatformEffect();
    }

    @Override
    public void applyEffect(Player player) {
        player.setVelocityY(jumpPlatformEffect);
    }
}
