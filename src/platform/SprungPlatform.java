package src.platform;

import src.player.Player;

public class SprungPlatform extends Platform {
    public SprungPlatform(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void applyEffect(Player player) {
        player.setVelocityY(-player.getJumpStrength()*2);
    }
}
