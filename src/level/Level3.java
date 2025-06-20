package src.level;

import src.music.Music;
import src.sounds.Sounds;
import src.platform.*;

import java.awt.*;

public class Level3 extends Level {
    public Level3(String title) {
        super(title);
        this.id = 3;
        this.groundY = 720;
        this.platformWidth = 100;
        this.platformHeight = 20;
        this.skyHeight = 720;
        setPlayerStartY(groundY - 32 - platformHeight);

        this.groundImage = loadImage("src/images/ground_Level3.png");
        this.skyImage = loadImage("src/images/sky_Level3.png");
        this.player1Image = loadImage("src/images/player1_level3.png");
        this.player2Image = loadImage("src/images/player2_level3.png");

        this.backgroundMusic = new Music("src/music/level3_music.wav");
        backgroundMusic.setVolume(0.7);
        this.respawnSound = new Sounds("src/sounds/respawnLevel3_sound.wav");

        this.platformSpeed = 2;
        this.platformColor = Color.white;
        platforms.add(new CheckpointPlatform(0, groundY - platformHeight, platformWidth, platformHeight, this)); // STANDPLATFORM
        platforms.add(new Platform(140, 600, platformWidth, platformHeight, this));
        platforms.add(new BoostPlatform(320, 650, platformWidth, platformHeight, this));
        platforms.add(new Platform(750, 700, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(900, 600, platformWidth / 2, platformHeight, this));
        platforms.add(new JumpPlatform(1050, 400, platformWidth / 2, platformHeight, this));
        platforms.add(new CheckpointPlatform(1200, 200, platformWidth / 3, platformHeight, this));
        platforms.add(new MovingPlatform(1250, 450, platformWidth * 2, platformHeight, this, 1, true, 1250, 1400));
        platforms.add(new Platform(1600, 450, platformWidth / 3, platformHeight, this));
        platforms.add(new Platform(1700, 450, platformWidth / 3, platformHeight, this));
        platforms.add(new DeathPlatform(1780, 550, platformWidth / 2, platformHeight, this));
        platforms.add(new CheckpointPlatform(1830, 550, platformWidth / 3, platformHeight, this));
        platforms.add(new Platform(1960, 500, platformWidth, platformHeight, this));
        platforms.add(new EntityPlatform(2110, 400, platformWidth * 3, platformHeight, this));
        entities.add(new Entity(platforms.getLast(), 1));
        platforms.add(new JumpPlatform(2550, 700, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(2800, 700, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(3000, 500, platformWidth, platformHeight, this));
        platforms.add(new BoostPlatform(3250, 500, platformWidth, platformHeight, this));
        platforms.add(new CheckpointPlatform(3800, 700, platformWidth * 2, platformHeight, this));
        platforms.add(new JumpPlatform(4050, 600, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(4250, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(4500, 400, platformWidth, platformHeight, this));

        this.goalX = platforms.getLast().getX() + platforms.getLast().getWidth() / 2 - 16;
        this.goalY = platforms.getLast().getY();
    }
}