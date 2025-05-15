package src;

import java.awt.*;

public class Level1 extends Level {
    public Level1(String title) {
        super(title);
        this.groundY = 700;
        this.platformWidth = 150;
        this.platformHeight = 20;
        this.groundImage = loadImage("src/images/groundLevel1.png");
        this.skyImage = loadImage("src/images/skyLevel1.png");
        this.player1Image = loadImage("src/images/player1.png");
        this.player2Image = loadImage("src/images/player2.png");
        this.platformColor = Color.BLUE;
        // Plattformen hinzufügen
        platforms.add(new Platform(200, 600, platformWidth, platformHeight));
        platforms.add(new Platform(450, 500, platformWidth, platformHeight));
        platforms.add(new Platform(750, 500, platformWidth, platformHeight));
    }
}





