package src.level;

import src.music.Music;
import src.sounds.Sounds;
import src.platform.*;

import java.awt.*;

public class Level2 extends Level {

    public Level2(String title) {
        super(title);
        this.id = 2;
        this.gravity = 0.25;
        this.groundY = 832;
        this.skyHeight = 800;
        this.platformWidth = 120;
        this.playerSpeed = 5;
        this.setPlayerStartY(groundY - 164);
        this.jumpPlatformEffect = getJumpPlatformEffect() / 1.5;

        this.skyImage = loadImage("src/images/sky_Level2.png");
        this.player1Image = loadImage("src/images/player1_level2.png");
        this.player2Image = loadImage("src/images/player2_level2.png");

        this.backgroundMusic = new Music("src/music/level2_music.wav");
        backgroundMusic.setVolume(0.8);
        this.respawnSound = new Sounds("src/sounds/respawn_sound.wav");

        this.platformColor = new Color(220, 203, 255);
        this.platformSpeed = 3;
        platforms.add(new CheckpointPlatform(0, 700, platformWidth, platformHeight, this));
        platforms.add(new Platform(500, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(1020, 500, platformWidth, platformHeight, this));
        platforms.add(new EntityPlatform(1250, 300, platformWidth * 2, platformHeight, this));
        entities.add(new Entity(platforms.getLast(), 2));
        platforms.add(new CheckpointPlatform(1490, 300, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(1950, 800, platformWidth * 3, platformHeight, this));
        platforms.add(new Platform(2650, 650, platformWidth, platformHeight, this));
        platforms.add(new DeathPlatform(3100, 500, platformWidth, platformHeight, this));
        platforms.add(new Platform(3100, 340, platformWidth, platformHeight, this));
        platforms.add(new CheckpointPlatform(3100, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(3690, 350, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(4360, 650, platformWidth, platformHeight, this));
        platforms.add(new Platform(4900, 350, platformWidth, platformHeight, this));

        this.goalX = platforms.getLast().getX() + platforms.getLast().getWidth() / 2 - 16;
        this.goalY = platforms.getLast().getY();
    }
}