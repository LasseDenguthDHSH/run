package src;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Player player1;
    private Player player2;
    private Level currentLevel;
    private Steuerung steuerung;
    private Timer gameTimer;
    private int cameraX = 0;



    public GamePanel(Level level) {
        this.currentLevel = level;

        this.steuerung = new Steuerung(this);
        this.addKeyListener(steuerung);
        this.setFocusable(true);

        this.player1 = new Player(32, 32, currentLevel.getPlayer1StartX(), currentLevel.getPlayer1StartY(), 5, currentLevel.getPlayer1Image());
        this.player2 = new Player(32, 32, currentLevel.getPlayer2StartX(), currentLevel.getPlayer2StartY(), 5, currentLevel.getPlayer2Image());

        gameTimer = new Timer(16, e -> update());
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Hintergrund
        g2.drawImage(currentLevel.getGroundImage(), -cameraX, currentLevel.getGroundY(), getWidth(), 100, this);
        g2.drawImage(currentLevel.getGroundImage(), getWidth()-cameraX, currentLevel.getGroundY(), getWidth(), 100, this);
        g2.drawImage(currentLevel.getGroundImage(), getWidth()*2-cameraX, currentLevel.getGroundY(), getWidth(), 100, this);
        g2.drawImage(currentLevel.getSkyImage(), -cameraX, 0, getWidth(), 700, this);
        g2.drawImage(currentLevel.getSkyImage(), getWidth()-cameraX, 0, getWidth(), 700, this);
        g2.drawImage(currentLevel.getSkyImage(), getWidth()*2-cameraX, 0, getWidth(), 700, this);
        g2.drawImage(currentLevel.getLevel2Image(), getWidth()*2-cameraX, 0, getWidth(), 800, this);

        // Plattformen
        for (Platform platform : currentLevel.getPlatforms()) {
            g2.setColor(currentLevel.getPlatformColor());
            g2.fillRect(platform.x - cameraX, platform.y, platform.width, platform.height);
        }

        // Player
        g2.drawImage(player1.getImage(), player1.x - cameraX, player1.y, player1.width, player1.height, this);
        g2.drawImage(player2.getImage(), player2.x - cameraX, player2.y, player2.width, player2.height, this);
    }

    public void update() {
        // Bewegung von Spieler 1
        if (steuerung.isRight1Pressed()) {
            player1.moveRight();
        }
        if (steuerung.isLeft1Pressed()) {
            player1.moveLeft();
        }
        if (steuerung.isUp1Pressed()){
            player1.jump();
        }
        player1.applyGravity(currentLevel);

        // Bewegung von Spieler 2
        if (steuerung.isRight2Pressed()) {
            player2.moveRight();
        }
        if (steuerung.isLeft2Pressed()) {
            player2.moveLeft();
        }
        if (steuerung.isUp2Pressed()){
            player2.jump();
        }
        player2.applyGravity(currentLevel);

        // Immer den Spieler mit der höchsten X-Position auswählen
        Player leadingPlayer = (player1.x > player2.x) ? player1 : player2;

        // Kamera folgt dem führenden Spieler in Echtzeit
        cameraX = leadingPlayer.x - (getWidth() / 2) + leadingPlayer.width / 2;

        // Falls dein Level eine feste Breite hat, Kamera begrenzen
        cameraX = Math.max(0, Math.min(cameraX, getWidth()*2));

        repaint();

    }
}