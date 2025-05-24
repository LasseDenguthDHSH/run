package src.level;

import src.platform.*;

import java.awt.*;

public class Level1 extends Level {
    public Level1(String title) {
        super(title);
        this.groundY = 832;
        this.skyHeight = 800;
        this.setPlayerStartY(groundY-164);
        this.groundImage = loadImage("src/images/groundLevel1.png");
        this.skyImage = loadImage("src/images/skyLevel1.png");
        this.player1Image = loadImage("src/images/player1.png");
        this.player2Image = loadImage("src/images/player2.png");
        this.platformColor = new Color(30, 30, 100);

        // Plattformen hinzufügen
        platforms.add(new Platform(0, 700, platformWidth, platformHeight, this));
        platforms.add(new Platform(200, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(400, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(690, 500, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(950, 500, platformWidth, platformHeight, this));
        platforms.add(new BoostPlatform(1250, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(1950, 600, platformWidth*3, platformHeight, this));
    }
}





