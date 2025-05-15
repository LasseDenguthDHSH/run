package src.level;

import src.platform.Platform;
import src.platform.SprungPlatform;

import java.awt.*;

public class Level1 extends Level {
    public Level1(String title) {
        super(title);
        this.groundY = 700;
        this.platformWidth = 100;
        this.platformHeight = 20;
        this.skyHeight = 700;
        this.setPlayerStartY(groundY-32);
        this.groundImage = loadImage("src/images/groundLevel1.png");
        this.skyImage = loadImage("src/images/skyLevel1.png");
        this.player1Image = loadImage("src/images/player1.png");
        this.player2Image = loadImage("src/images/player2.png");
        this.platformColor = Color.BLUE;
        // Plattformen hinzufügen
        platforms.add(new Platform(200, 600, platformWidth, platformHeight));
        platforms.add(new Platform(400, 500, platformWidth, platformHeight));
        platforms.add(new Platform(690, 500, platformWidth, platformHeight));
        platforms.add(new SprungPlatform(800, 500, platformWidth, platformHeight));
    }
}





