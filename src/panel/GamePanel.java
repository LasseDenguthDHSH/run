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
    private int cameraX = 0;
    private Player leadingPlayer;
    private Image pfeil;

    private Stoppuhr stoppuhr;
    private boolean timerStarted = false;

    public GamePanel(Level level) {
        this.currentLevel = level;
        this.pfeil = new ImageIcon("src/images/pfeil.png").getImage();
        this.steuerung = new Steuerung();
        this.addKeyListener(steuerung);
        this.setFocusable(true);

        this.player1 = new Player(32, 32, currentLevel, 5, currentLevel.getPlayer1Image());
        this.player2 = new Player(32, 32, currentLevel, 5, currentLevel.getPlayer2Image());

        this.stoppuhr = new Stoppuhr(); // dein Stoppuhr zur Zeitmessung

        gameTimer = new Timer(16, e -> update());
        gameTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Hintergrund
        g2.drawImage(currentLevel.getGroundImage(), -cameraX, currentLevel.getGroundY(), getWidth(), 100, this);
        g2.drawImage(currentLevel.getGroundImage(), getWidth() - cameraX, currentLevel.getGroundY(), getWidth(), 100, this);
        g2.drawImage(currentLevel.getGroundImage(), getWidth() * 2 - cameraX, currentLevel.getGroundY(), getWidth(), 100, this);
        g2.drawImage(currentLevel.getGroundImage(), getWidth() * 3 - cameraX, currentLevel.getGroundY(), getWidth(), 100, this);
        g2.drawImage(currentLevel.getSkyImage(), -cameraX, 0, getWidth()*2, currentLevel.getSkyHeight(), this);
        g2.drawImage(currentLevel.getSkyImage(), getWidth()*2 - cameraX, 0, getWidth()*2, currentLevel.getSkyHeight(), this);

        // Plattformen
        for (Platform platform : currentLevel.getPlatforms()) {
            g2.setColor(platform.getPlatformColor());
            g2.fillRect(platform.getX() - cameraX, platform.getY(), platform.getWidth(), platform.getHeight());
        }


        // Player
        g2.drawImage(player1.getImage(), player1.getX() - cameraX, player1.getY(), player1.getWidth(), player1.getHeight(), this);
        g2.drawImage(player2.getImage(), player2.getX() - cameraX, player2.getY(), player2.getWidth(), player2.getHeight(), this);


        // Stoppuhr-Anzeige
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Zeit: " + stoppuhr.getFormattedTime(), 20, 30);

        //Level Spezifisch
        if (currentLevel instanceof Level2){
            g2.drawImage(pfeil, 2080 - cameraX, getHeight()/2, pfeil.getWidth(this)* 3/7,
                    pfeil.getHeight(this)* 3/7, this);
        }
    }


    public void update() {
        boolean moved = false;

        // Spieler 1 Steuerung
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
        player1.boostMovement();
        player1.applyGravity(currentLevel);

        // Spieler 2 Steuerung
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
        player2.boostMovement();
        player2.applyGravity(currentLevel);

        // Stoppuhr starten bei erster Bewegung
        if (!timerStarted && moved) {
            stoppuhr.start();
            timerStarted = true;
        }

        // Immer den Spieler mit der höchsten X-Position auswählen
        leadingPlayer = (player1.getX() > player2.getX()) ? player1 : player2;

        // Kamera folgt dem führenden Spieler
        cameraX = leadingPlayer.getX() - (getWidth() / 2) + leadingPlayer.getWidth() / 2;

        // Kamera begrenzen
        cameraX = Math.max(0, Math.min(cameraX, getWidth() * 3));

        if (steuerung.isEscapePressed()) {
            Main.showMenu();
            gameTimer.stop();
        }

        repaint();

    }

    public Player getLeadingPlayer() {
        return leadingPlayer;
    }
}