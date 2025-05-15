package src.platform;

import src.player.Player;

import java.awt.*;

public class SprungPlatform extends Platform {
    public SprungPlatform(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.platformColor = new Color(200, 100, 0);
    }

    @Override
    public void applyEffect(Player player) {
        player.setVelocityY(-player.getJumpStrength()*2);
    }
}
