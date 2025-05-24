package src.level;

import src.platform.*;

public class Level3 extends Level{
    public Level3(String title) {
        super(title);
        this.groundY = 700;
        this.platformWidth = 150;
        this.platformHeight = 20;
        this.skyHeight = 700;
        setPlayerStartY(groundY-32 - platformHeight);
        this.groundImage = loadImage("src/images/groundLevel3.png");
        this.skyImage = loadImage("src/images/sky_Level3.png");
        this.player1Image = loadImage("src/images/player1.png");
        this.player2Image = loadImage("src/images/player2.png");

        platforms.add(new Platform(0, groundY-platformHeight, platformWidth, platformHeight, this)); // STANDPLATFORM
        platforms.add(new Platform(300, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(600, 300, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(800, 600, platformWidth, platformHeight, this));
    }
}
