package src;

import javax.swing.*;
import java.awt.*;

public class Level2 extends Level {
    public Level2(String title) {
        super(title);
        this.groundY = 700;
        this.groundImage = loadImage("src/images/groundLevel2.png");
        this.skyImage = loadImage("src/images/skyLevel2.png");
        this.player1Image = loadImage("src/images/player1.png");
        this.player2Image = loadImage("src/images/player2.png");
        this.platformColor = Color.gray;
        // Plattformen hinzufügen
        platforms.add(new Platform(200, 600, 150, 20));
        platforms.add(new Platform(450, 500, 150, 20));
    }
}





