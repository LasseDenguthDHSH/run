package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Level {
    int groundY;
    int platformWidth;
    int platformHeight;
    Image groundImage;
    Image skyImage;
    Image player1Image;
    Image player2Image;
    ArrayList<Platform> platforms = new ArrayList<>();
    String title;
    Color platformColor;

    public Level(String title) {
        this.title = title;
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

    public int getGroundY() {
        return groundY;
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

    public int getPlayer1StartX() {
        return 50;
    }

    public int getPlayer1StartY() {
        return groundY - 32;
    }

    public int getPlayer2StartX() {
        return 50;
    }

    public int getPlayer2StartY() {
        return groundY - 32;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public Color getPlatformColor() {
        return platformColor;
    }

}