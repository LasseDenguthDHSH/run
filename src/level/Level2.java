package src.level;

import src.platform.*;
import java.awt.*;

public class Level2 extends Level {
    Image pfeil;
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
        this.jumpPlatformEffect= getJumpPlatformEffect()/1.5;
        this.pfeil = loadImage("src/images/pfeil.png");

        // Plattformen hinzufügen
        platforms.add(new CheckpointPlatform(0, 700, platformWidth, platformHeight, this));
        platforms.add(new Platform(550, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(1150, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(1650, 300, platformWidth, platformHeight, this));
        platforms.add(new SprungPlatform(1950, 800, platformWidth*3, platformHeight, this));
        platforms.add(new Platform(2750, 650, platformWidth, platformHeight, this));
        platforms.add(new DeathPlatform(3200, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(3200, 300, platformWidth, platformHeight, this));
        platforms.add(new CheckpointPlatform(3200, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(3840, 350, platformWidth+30, platformHeight, this));
        platforms.add(new SprungPlatform(4540, 350, platformWidth, platformHeight, this));

    }
}





