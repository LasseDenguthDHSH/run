package src.platform;

import src.player.Player;

import java.awt.*;

public class BoostPlatform extends Platform {
    public BoostPlatform(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.platformColor = new Color(0, 200, 150);
    }
    @Override
    public void applyEffect(Player player) {
        player.moveRight(player.getSpeed()*100);
    }
}
