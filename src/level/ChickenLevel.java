package src.level;
import src.music.Music;
import src.sounds.Sounds;


public class ChickenLevel extends Level{

    int bullets;
    int hits = 0;

    public ChickenLevel(String title) {
        super(title);

        this.groundY = 700;
        this.skyHeight = 800;
        this.playerStartY = groundY - 32;
        this.playerStartX = 784;
        this.skyImage = loadImage("src/images/sky_Level1.png");
        this.bullets = 5;
        this.playerSpeed = 2;
        this.gravity = 0.4;
        this.playerJumpStrength = 10;

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

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
}
