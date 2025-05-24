package src.panel;

import src.*;
import src.level.*;
import src.platform.Platform;
import src.player.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Player player1;
    private Player player2;
    private Level currentLevel;
    private Steuerung steuerung;
    private Timer gameTimer;
    private int cameraX1;
    private int cameraX2;
    private Stoppuhr stoppuhr;
    private boolean timerStarted = false;
    private Image pfeil;

    public GamePanel(Level level) {
        this.currentLevel = level;
        this.steuerung = new Steuerung();
        this.addKeyListener(steuerung);
        this.setFocusable(true);

        this.player1 = new Player(32, 32, currentLevel, currentLevel.getPlayer1Image());
        this.player2 = new Player(32, 32, currentLevel, currentLevel.getPlayer2Image());

        this.pfeil = new ImageIcon("src/images/pfeil.png").getImage();
        this.stoppuhr = new Stoppuhr();

        cameraX1 = Math.max(player1.getX() - (getWidth() / 4), 0);
        cameraX2 = Math.max(player2.getX() - (getWidth() / 4), 0) - (getWidth() / 2);

        gameTimer = new Timer(16, e -> update());
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int halfWidth = getWidth() / 2;

        // Linke Hälfte für Spieler 1
        g2.setClip(0, 0, halfWidth, getHeight());
        renderScene(g2, player1, cameraX1, 60);

        // Rechte Hälfte für Spieler 2
        g2.setClip(halfWidth, 0, halfWidth, getHeight());
        renderScene(g2, player2, cameraX2, halfWidth + 60);

        // Split
        g2.setClip(null);
        g2.setColor(Color.white);
        g2.drawLine(getWidth() / 2, 58, getWidth() / 2, getHeight());
        // Stoppuhr
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(stoppuhr.getFormattedTime(), halfWidth-31, 32);

    }

    private void renderScene(Graphics2D g2, Player player, int cameraX, int abstand) {
        // Kamera für Spieler 1
        if (player == player1) {
            cameraX = cameraX1;
        }
        // Kamera für Spieler 2
        else {
            cameraX = cameraX2;
        }

        cameraX = Math.min(cameraX, getWidth() * 6);

        // Umgebung
        g2.drawImage(currentLevel.getGroundImage(), -cameraX, currentLevel.getGroundY(), getWidth() * 2, 100, this);
        g2.drawImage(currentLevel.getGroundImage(), -cameraX + getWidth() * 2, currentLevel.getGroundY(), getWidth() * 2, 100, this);
        g2.drawImage(currentLevel.getGroundImage(), -cameraX + getWidth() * 4, currentLevel.getGroundY(), getWidth() * 2, 100, this);
        g2.drawImage(currentLevel.getSkyImage(), -cameraX, 0, getWidth() * 2, currentLevel.getSkyHeight(), this);
        g2.drawImage(currentLevel.getSkyImage(), -cameraX + getWidth() * 2, 0, getWidth() * 2, currentLevel.getSkyHeight(), this);
        g2.drawImage(currentLevel.getSkyImage(), -cameraX + getWidth() * 4, 0, getWidth() * 2, currentLevel.getSkyHeight(), this);

        for (Platform platform : currentLevel.getPlatforms()) {
            g2.setColor(platform.getPlatformColor());
            g2.fillRect(platform.getX() - cameraX, platform.getY(), platform.getWidth(), platform.getHeight());
        }

        // Spieler
        g2.drawImage(player.getImage(), player.getX() - cameraX, player.getY(), player.getWidth(), player.getHeight(), this);


        // Fortschrittsbalken
        int progressBarWidth = getWidth() / 2 - 120;
        int progressBarHeight = 8;
        int startY = 18;
        int zielX = currentLevel.getZielX();
        int progress = (int) ((double) player.getX() / zielX * progressBarWidth);
        g2.setColor(Color.WHITE);
        g2.drawRect(abstand, startY, progressBarWidth, progressBarHeight);
        g2.setColor(Color.GREEN);
        g2.fillRect(abstand, startY, progress, progressBarHeight);

        //Level Spezifisch
        if (currentLevel instanceof Level2) {
            g2.drawImage(pfeil, 2080 - cameraX, getHeight() / 2, pfeil.getWidth(this) * 3 / 7, pfeil.getHeight(this) * 3 / 7, this);
        }
    }

    public void update() {
        boolean moved = false;

        // **Steuerung für Spieler 1**
        if (steuerung.isRight1Pressed()) {
            player1.moveRight(player1.getSpeed());
            moved = true;
        }
        if (steuerung.isLeft1Pressed()) {
            player1.moveLeft(player1.getSpeed());
            moved = true;
        }
        if (steuerung.isUp1Pressed()) {
            player1.jump();
            moved = true;
        }

        // **Steuerung für Spieler 2**
        if (steuerung.isRight2Pressed()) {
            player2.moveRight(player2.getSpeed());
            moved = true;
        }
        if (steuerung.isLeft2Pressed()) {
            player2.moveLeft(player2.getSpeed());
            moved = true;
        }
        if (steuerung.isUp2Pressed()) {
            player2.jump();
            moved = true;
        }

        player1.applyGravity(currentLevel);
        player2.applyGravity(currentLevel);
        player1.boostMovement();
        player2.boostMovement();

        // **Stoppuhr starten bei erster Bewegung**
        if (!timerStarted && moved) {
            stoppuhr.start();
            timerStarted = true;
        }

        // **Fixierte Kamera für Spieler 2 von Anfang an!**
        cameraX1 = Math.max(player1.getX() - (getWidth() / 4), 0);
        cameraX2 = Math.max(player2.getX() - (getWidth() / 4), 0) - (getWidth() / 2); // **Fix: Spieler 2 bleibt sichtbar!**

        cameraX1 = Math.min(cameraX1, getWidth() * 3);
        cameraX2 = Math.min(cameraX2, getWidth() * 3);

        if (steuerung.isEscapePressed()) {
            Main.showMenu();
            gameTimer.stop();
        }

        repaint();
    }
}
