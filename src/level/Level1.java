package src.level;

import src.Music;
import src.panel.GamePanel;
import src.platform.*;

import java.awt.*;

public class Level1 extends Level {
    private Music backgroundMusic;

    public Level1(String title) {
        super(title);
        this.groundY = 832;
        this.skyHeight = 800;
        this.setPlayerStartY(groundY-164);
        this.skyImage = loadImage("src/images/sky_Level1.png");
        this.player1Image = loadImage("src/images/player1_level1.png");
        this.player2Image = loadImage("src/images/player2_level1.png");
        this.platformColor = new Color(30, 30, 100);

        // Hintergrundmusik starten
        backgroundMusic = new Music("src/music/background.wav");
        backgroundMusic.play();

        // Plattformen hinzufügen
        platforms.add(new Platform(0, 700, platformWidth, platformHeight, this));
        platforms.add(new MovingPlatform(300, 600, platformWidth, platformHeight, this, 1, false, 120, 580));
        platforms.add(new Platform(600, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(750, 500, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(1000, 700, platformWidth/2, platformHeight, this));
        platforms.add(new BoostPlatform(1150, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(1800, 750, platformWidth*2, platformHeight, this));
        platforms.add(new Platform(2050, 650, platformWidth, platformHeight, this));
        platforms.add(new Platform(2300, 750, platformWidth, platformHeight, this));
        platforms.add(new DeathPlatform(2300, 650, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(2500, 700, platformWidth/2, platformHeight, this));
        platforms.add(new Platform(2700, 500, platformWidth, platformHeight, this));
        platforms.add(new MovingPlatform(2800, 400, platformWidth*2, platformHeight, this, 1, true, 2800, 3250));
        platforms.add(new BoostPlatform(3400, 700, platformWidth, platformHeight, this));
        platforms.add(new Platform(3850, 750, platformWidth*2, platformHeight, this));
        platforms.add(new JumpPlatform(4050, 800, platformWidth*2, platformHeight, this));
        platforms.add(new JumpPlatform(4300, 600, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(4550, 400, platformWidth, platformHeight, this));
        platforms.add(new Platform(4850, 150, platformWidth, platformHeight, this));
        platforms.add(new Platform(5100, 150, platformWidth, platformHeight, this));
        this.platformColor = new Color(0,0,0);
        platforms.add(new Platform(5430, 700, platformWidth, platformHeight/2, this));




        this.zielX = platforms.getLast().getX()+platforms.getLast().getWidth()/2-16;
        this.zielY = platforms.getLast().getY();
    }
}