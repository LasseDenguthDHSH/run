package src;

import javax.swing.*;
import java.awt.*;

public class Level1 extends Level {
    public Level1(String title) {
        super(title);
        this.groundY = 700;
        this.groundImage = loadImage("src/images/ground.png");
        this.skyImage = loadImage("src/images/sky.png");
        this.player1Image = loadImage("src/images/player1.png");
        this.player2Image = loadImage("src/images/player2.png");
        this.platformColor = Color.BLUE;
        // Plattformen hinzufügen
        platforms.add(new Platform(200, 600, 150, 20));
        platforms.add(new Platform(450, 500, 150, 20));
    }
}





