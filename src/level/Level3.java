package src.level;

import src.platform.Platform;

import java.awt.*;

public class Level3 extends Level{
    public Level3(String title) {
        super(title);
        this.groundY = 700;
        this.platformWidth = 150;
        this.platformHeight = 20;
        this.skyHeight = 700;
        this.setPlayerStartY(groundY-32 - platformHeight);
        this.groundImage = loadImage("src/images/groundLevel3.png");
        this.skyImage = loadImage("src/images/skyLevel3.png");
        this.player1Image = loadImage("src/images/player1.png");
        this.player2Image = loadImage("src/images/player2.png");
        this.platformColor = new Color(255, 255, 255);
        platforms.add(new Platform(0, groundY-platformHeight, platformWidth, platformHeight));
        platforms.add(new Platform(450, 500, platformWidth, platformHeight));
        platforms.add(new Platform(750, 500, platformWidth, platformHeight));
    }
}
