package src.level;

import src.music.Music;
import src.sounds.Sounds;
import src.platform.*;

import java.awt.*;

public class Level1 extends Level {

    public Level1(String title) {
        super(title);
        this.id = 1;
        this.groundY = 832;
        this.skyHeight = 800;
        this.setPlayerStartY(groundY - 164);

        this.skyImage = loadImage("src/images/sky_Level1.png");
        this.player1Image = loadImage("src/images/player1_level1.png");
        this.player2Image = loadImage("src/images/player2_level1.png");

        this.backgroundMusic = new Music("src/music/level1_music.wav");
        backgroundMusic.setVolume(0.6);
        this.respawnSound = new Sounds("src/sounds/respawn_sound.wav");

        this.platformColor = new Color(30, 30, 100);
        this.platformSpeed = 3;
        platforms.add(new Platform(0, 700, platformWidth, platformHeight, this));
        platforms.add(new MovingPlatform(350, 600, platformWidth * 2, platformHeight, this, 1, false, 120, 580 - platformWidth));
        platforms.add(new EntityPlatform(650, 500, platformWidth * 3, platformHeight, this));
        entities.add(new Entity(platforms.getLast(), 1));
        platforms.add(new Platform(1000, 700, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(1120, 600, platformWidth / 2, platformHeight, this));
        platforms.add(new BoostPlatform(1250, 600, platformWidth, platformHeight, this));
        platforms.add(new Platform(1750, 750, platformWidth * 2, platformHeight, this));
        platforms.add(new Platform(2000, 650, platformWidth, platformHeight, this));
        platforms.add(new Platform(2150, 750, platformWidth, platformHeight, this));
        platforms.add(new DeathPlatform(2150, 650, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(2320, 700, platformWidth / 2, platformHeight, this));
        platforms.add(new Platform(2520, 500, platformWidth, platformHeight, this));
        platforms.add(new MovingPlatform(2820, 400, platformWidth * 2, platformHeight, this, 1, true, 2620, 3000));
        platforms.add(new BoostPlatform(3250, 700, platformWidth, platformHeight, this));
        platforms.add(new Platform(3650, 750, platformWidth * 2, platformHeight, this));
        platforms.add(new JumpPlatform(3850, 800, platformWidth * 2, platformHeight, this));
        platforms.add(new JumpPlatform(4000, 600, platformWidth, platformHeight, this));
        platforms.add(new JumpPlatform(4150, 400, platformWidth, platformHeight, this));
        platforms.add(new Platform(4300, 150, platformWidth, platformHeight, this));
        platforms.add(new Platform(4460, 150, platformWidth, platformHeight, this));
        this.platformColor = new Color(0, 0, 0);
        platforms.add(new Platform(4730, 700, platformWidth, platformHeight / 2, this));

        this.goalX = platforms.getLast().getX() + platforms.getLast().getWidth() / 2 - 16;
        this.goalY = platforms.getLast().getY();
    }
}