package src;

import javax.swing.*;

public class Level1 extends Level {
    @Override
    public void setupLevel() {
        groundY = 500;
        groundImage = loadImage("src/images/unbenannt.png");
        player1Image = loadImage("src/images/player1.png");
        player2Image = loadImage("src/images/player2.png");
    }
}




