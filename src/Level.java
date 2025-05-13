package src;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public abstract class Level {
    protected int groundY;
    protected Image groundImage;
    protected Image player1Image;
    protected Image player2Image;

    public abstract void setupLevel();

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
}