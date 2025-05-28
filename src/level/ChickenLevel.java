package src.level;
import src.music.Music;
import src.sounds.Sounds;


public class ChickenLevel extends Level{

    int bullets;

    public ChickenLevel(String title) {
        super(title);

        this.groundY = 700;
        this.skyHeight = 800;
        this.playerStartY = groundY - 32;
        this.playerStartX = 784;
        this.skyImage = loadImage("src/images/sky_Level1.png");
        this.bullets = 5;
        this.playerSpeed = 7;
        this.gravity = 3;
        this.playerJumpStrength = 20;

        this.respawnSound = new Sounds("src/sounds/respawn_sound.wav");
        this.backgroundMusic = new Music("src/music/background.wav");
        backgroundMusic.setVolume(0.6);

    }
    public int getBullets() {
        return bullets;
    }
    public void setBullets(int bullets) {
        this.bullets = bullets;
    }
}
