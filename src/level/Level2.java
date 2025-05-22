package src.level;

import src.platform.CheckpointPlatform;
import src.platform.DeathPlatform;
import src.platform.Platform;
import src.platform.SprungPlatform;
import src.player.Player;

import java.awt.*;

public class Level2 extends Level {
    public Level2(String title) {
        super(title);
        this.gravity = 0.25;
        this.groundY = 832;
        this.skyHeight = 800;
        this.platformWidth = 120;
        this.setPlayerStartY(groundY-164);
        this.groundImage = loadImage("src/images/groundLevel2.png");
        this.skyImage = loadImage("src/images/skyLevel2.png");
        this.player1Image = loadImage("src/images/player1_level2.png");
        this.player2Image = loadImage("src/images/player2_level2.png");
        this.platformColor = new Color(220, 203, 255);
        // Plattformen hinzufügen
        platforms.add(new CheckpointPlatform(0, 700, platformWidth, platformHeight, this));
        platforms.add(new Platform(550, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(1150, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(1650, 300, platformWidth, platformHeight, this));
        platforms.add(new SprungPlatform(1850, 800, platformWidth*3, platformHeight, this));
        platforms.add(new Platform(2850, 650, platformWidth, platformHeight, this));
        platforms.add(new Platform(3150, 350, platformWidth, platformHeight, this));
        platforms.add(new Platform(3150, 100, platformWidth, platformHeight, this));
        platforms.add(new CheckpointPlatform(3150, 450, platformWidth, platformHeight, this));
        platforms.add(new Platform(3790, 350, platformWidth/2, platformHeight, this));
        platforms.add(new DeathPlatform(3790+60, 350, platformWidth*3, platformHeight, this));
        platforms.add(new Platform(3850+360, 350, platformWidth/2, platformHeight, this));
        platforms.add(new DeathPlatform(4210+60, 350, platformWidth*3, platformHeight, this));
        platforms.add(new Platform(4270+360, 350, platformWidth/2, platformHeight, this));
        platforms.add(new SprungPlatform(4890, 750, platformWidth, platformHeight, this));

    }
}





