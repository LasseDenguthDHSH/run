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
        platforms.add(new JumpPlatform(200-70, 600+80, platformWidth, platformHeight, this));
        platforms.add(new Platform(400-70, 500+80, platformWidth, platformHeight, this));
        platforms.add(new Platform(690-70, 500+80, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(950-70, 500 +80, platformWidth, platformHeight, this));
        platforms.add(new BoostPlatform(1250-70, 500+80, platformWidth, platformHeight, this));
        platforms.add(new Platform(1950-70, 600+80, platformWidth*3, platformHeight, this));
    }
}





