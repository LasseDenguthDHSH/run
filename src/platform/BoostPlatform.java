package src.platform;

import src.level.Level;
import src.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoostPlatform extends Platform {
    public BoostPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = new Color(0, 200, 150);
    }
    @Override
    public void applyEffect(Player player) {
        player.setVelocityX(player.getSpeed() * 10); // Spieler bekommt einen Boost nach rechts
    }
}
