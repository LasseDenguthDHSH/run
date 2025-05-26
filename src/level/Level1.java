package src.level;

import src.platform.*;

import java.awt.*;

public class Level1 extends Level {
    public Level1(String title) {
        super(title);
        this.groundY = 832;
        this.skyHeight = 800;
        this.setPlayerStartY(groundY-164);
        this.skyImage = loadImage("src/images/sky_Level1.png");
        this.player1Image = loadImage("src/images/player1_level1.png");
        this.player2Image = loadImage("src/images/player2_level1.png");
        this.platformColor = new Color(30, 30, 100);

        // Plattformen hinzufügen
        platforms.add(new Platform(0, 700, platformWidth, platformHeight, this));
        platforms.add(new MovingPlatform(150, 600, platformWidth, platformHeight, this, 1, true, 150, 550));
        platforms.add(new Platform(600, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(750, 500, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(950, 700, platformWidth, platformHeight, this));
        platforms.add(new BoostPlatform(1150, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(1700, 750, platformWidth*3, platformHeight, this));
        platforms.add(new Platform(1700, 750, platformWidth, platformHeight, this));
    }
}