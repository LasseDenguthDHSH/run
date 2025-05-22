package src.platform;

import src.level.Level;
import src.player.Player;
import java.awt.*;

public class CheckpointPlatform extends Platform {
    public CheckpointPlatform(int x, int y, int width, int height, Level level) {
        super(x, y, width, height, level);
        this.platformColor = Color.GREEN; // Markiere die Plattform sichtbar als Checkpoint
    }

    @Override
    public void applyEffect(Player player) {
        player.setCheckpoint(getX()+getWidth()/2-16, getY()-32); // Speichert die Position des Checkpoints
    }
}

