package src.level;
import src.music.Music;
import src.sounds.Sounds;

import java.awt.*;


public class ChickenLevel extends Level{

    int bullets;
    int hits = 0;
    private Sounds gun_sound;
    private Sounds chicken_sound;
    private Image informations;

    public ChickenLevel(String title) {
        super(title);

        this.groundY = 700;
        this.skyHeight = 800;
        this.playerStartY = groundY-64;
        this.playerStartX = 742;
        this.skyImage = loadImage("src/images/kampfArena.gif");
        this.bullets = 5;
        this.playerSpeed = 2;
        this.gravity = 0.4;
        this.playerJumpStrength = 10;
        this.jumpSound = new Sounds("src/sounds/chickenJump_sound.wav");
        jumpSound.setVolume(0.8);

        this.respawnSound = new Sounds("src/sounds/respawn_sound.wav");
        this.gun_sound = new Sounds("src/sounds/gun_sound.wav");
        gun_sound.setVolume(0.7);
        this.chicken_sound = new Sounds("src/sounds/chicken_sound.wav");
        chicken_sound.setVolume(0.7);
        this.backgroundMusic = new Music("src/music/background.wav");
        backgroundMusic.setVolume(0.6);

        this.informations = loadImage("src/images/chickenInformation.png");

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

    public Sounds getGun_sound(){
        return gun_sound;
    }

    public Sounds getChicken_sound() {
        return chicken_sound;
    }

    public Image getInformations() {
        return informations;
    }
}
