package src.level;

import src.music.Music;
import src.sounds.Sounds;
import src.platform.*;

import java.awt.*;

public class Level3 extends Level{
    public Level3(String title) {
        super(title);
        this.groundY = 720;
        this.platformWidth = 150;
        this.platformHeight = 20;
        this.skyHeight = 720;
        setPlayerStartY(groundY-32 - platformHeight);
        this.groundImage = loadImage("src/images/ground_Level3.png");
        this.skyImage = loadImage("src/images/sky_Level3.png");
        this.player1Image = loadImage("src/images/player1_level3.png");
        this.player2Image = loadImage("src/images/player2_level3.png");
        this.platformColor = new Color(40, 40, 60);
        this.backgroundMusic = new Music("src/music/lava_music.wav");
        backgroundMusic.setVolume(0.7);
        this.respawnSound = new Sounds("src/sounds/respawnLevel3_sound.wav");
        this.platformSpeed = 2;


        platforms.add(new Platform(0, groundY-platformHeight, platformWidth, platformHeight, this)); // STANDPLATFORM
        platforms.add(new Platform(300, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(600, 300, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(800, 600, platformWidth, platformHeight, this));
        this.zielX = platforms.getLast().getX()+platforms.getLast().getWidth()/2 - 16;
        this.zielY = platforms.getLast().getY();
    }
}