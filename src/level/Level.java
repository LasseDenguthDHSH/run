package src.level;

import src.music.Music;
import src.sounds.Sounds;
import src.platform.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Level {
    protected String player1Name = "Spieler 1";
    protected String player2Name = "Spieler 2";
    int groundY;
    int platformWidth;
    int platformHeight;
    int skyHeight;
    int playerStartX;
    int playerStartY;
    int zielX;
    int zielY;
    double gravity;
    double playerJumpStrength;
    Image groundImage;
    Image skyImage;
    Image player1Image;
    Image player2Image;
    ArrayList<Platform> platforms = new ArrayList<>();
    String title;
    Color platformColor;
    double jumpPlatformEffect = -24;
    double playerSpeed;
    Music backgroundMusic;
    Sounds respawnSound;
    Sounds jumpSound;
    Sounds winSound;
    ArrayList<Entity> entities = new ArrayList<>();
    int platformSpeed;

    public Level(String title) {

        if(!(this instanceof ChickenLevel)){
            this.player1Name = JOptionPane.showInputDialog(null, "Name für Spieler 1:");
            if (player1Name == null || player1Name.trim().isEmpty()) player1Name = "Spieler 1";
            this.player2Name = JOptionPane.showInputDialog(null, "Name für Spieler 2:");
            if (player2Name == null || player2Name.trim().isEmpty()) player2Name = "Spieler 2";
        }
        this.title = title;
        this.playerStartX = 50;
        this.gravity = 0.7;
        this.platformWidth = 100;
        this.platformHeight = 18;
        this.playerSpeed = 3;
        this.playerJumpStrength = 11;
        this.jumpSound = new Sounds("src/sounds/jump.wav");
        this.winSound = new Sounds("src/sounds/win_sound.wav");
        winSound.setVolume(0.8);
    }

    protected Image loadImage(String absolutePath) {
        try {
            File file = new File(absolutePath);
            if (file.exists()) {
                return ImageIO.read(file);
            } else {
                System.out.println("Bild nicht gefunden: " + file.getAbsolutePath());
                return null;
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Laden des Bildes: " + absolutePath);
            e.printStackTrace();
            return null;
        }
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int getGroundY() {
        return groundY;
    }
    public int getSkyHeight() {
        return skyHeight;
    }

    public Image getGroundImage() {
        return groundImage;
    }

    public Image getSkyImage() {
        return skyImage;
    }

    public Image getPlayer1Image() {
        return player1Image;
    }

    public Image getPlayer2Image() {
        return player2Image;
    }

    public int getPlayerStartX() {
        return playerStartX;
    }

    public int getPlayerStartY() {
        return playerStartY;
    }

    public void setPlayerStartY(int playerStartY) {
        this.playerStartY = playerStartY;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public Color getPlatformColor() {
        return platformColor;
    }

    public double getGravity() {
        return gravity;
    }

    public double getJumpPlatformEffect() {
        return jumpPlatformEffect;
    }
    public int getZielX() {
        return zielX;
    }
    public int getZielY() {
        return zielY;
    }
    public String getTitle() {
        return title;
    }

    public double getPlayerSpeed() {
        return playerSpeed;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public Sounds getRespawnSound() {
        return respawnSound;
    }

    public double getPlayerJumpStrength() {
        return playerJumpStrength;
    }

    public Sounds getJumpSound() {
        return jumpSound;
    }

    public Sounds getWinSound() {
        return winSound;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public int getPlatformSpeed() {
        return platformSpeed;
    }
}
