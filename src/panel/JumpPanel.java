package src.panel;

import src.*;
import src.level.*;
import src.platform.CheckpointPlatform;
import src.platform.Entity;
import src.platform.MovingPlatform;
import src.platform.Platform;
import src.player.*;

import javax.swing.*;
import java.awt.*;

public class JumpPanel extends JPanel {
    private int numBackgrounds;
    private int totalWidth;
    private int cameraX1;
    private int cameraX2;
    private long finishTime;
    private boolean timerStarted = false;
    private String player1Name;
    private String player2Name;
    private String finishTimeDisplay;
    private Player player1;
    private Player player2;
    private Player winner;
    private Player loser;
    private Level currentLevel;
    private Image arrowVertical;
    private Image arrowHorizontal;
    private Image goalFlag;
    private Image controlWASD;
    private Image controlArrows;
    private Controls controls;
    private Clock clock;
    private Timer gameTimer;

    public JumpPanel(Level level, JFrame frame) {
        this.currentLevel = level;
        this.controls = new Controls();
        this.addKeyListener(controls);
        this.setFocusable(true);

        this.numBackgrounds = currentLevel.getGoalX() / frame.getWidth() + 2;
        this.totalWidth = frame.getWidth() * numBackgrounds;

        this.player1Name = JOptionPane.showInputDialog(null, "Name für Spieler 1:");
        if (player1Name == null || player1Name.trim().isEmpty()) player1Name = "Spieler 1";
        this.player2Name = JOptionPane.showInputDialog(null, "Name für Spieler 2:");
        if (player2Name == null || player2Name.trim().isEmpty()) player2Name = "Spieler 2";

        this.player1 = new Player(player1Name, 32, 32, currentLevel, currentLevel.getPlayer1Image());
        this.player2 = new Player(player2Name, 32, 32, currentLevel, currentLevel.getPlayer2Image());

        this.arrowVertical = new ImageIcon("src/images/arrowVertical.png").getImage();
        this.arrowHorizontal = new ImageIcon("src/images/arrowHorizontal.png").getImage();
        this.goalFlag = new ImageIcon("src/images/goalflag.png").getImage();
        this.controlWASD = new ImageIcon("src/images/controlWASD.png").getImage();
        this.controlArrows = new ImageIcon("src/images/controlArrows.png").getImage();

        this.clock = new Clock();

        gameTimer = new Timer(16, e -> update());
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Linke Hälfte für Spieler 1
        g2.setClip(0, 0, getWidth() / 2, getHeight());
        renderScene(g2, player1, cameraX1, 60, controls);

        // Rechte Hälfte für Spieler 2
        g2.setClip(getWidth() / 2, 0, getWidth() / 2, getHeight());
        renderScene(g2, player2, cameraX2, getWidth() / 2 + 60, controls);

        // Split
        g2.setClip(null);
        g2.setColor(Color.white);
        g2.drawLine(getWidth() / 2, 58, getWidth() / 2, getHeight());
        // Stoppuhr
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(clock.getFormattedTime(), getWidth() / 2 - 31, 32);

    }

    private void renderScene(Graphics2D g2, Player player, int cameraX, int abstand, Controls controls) {
        // Kamera für Spieler 1
        if (player == player1) {
            cameraX1 = Math.max(player1.getX() - (getWidth() / 4), 0);
        }
        // Kamera für Spieler 2
        else {
            cameraX2 = Math.max(player2.getX() - (getWidth() / 4), 0) - (getWidth() / 2);
        }
        cameraX = Math.min(cameraX, totalWidth);

        // Umgebung
        for (int i = 0; i < numBackgrounds; i += 2) {
            g2.drawImage(currentLevel.getGroundImage(), -cameraX + getWidth() * i, currentLevel.getGroundY(), getWidth() * 2, 100, this);
            g2.drawImage(currentLevel.getSkyImage(), -cameraX + getWidth() * i, 0, getWidth() * 2, currentLevel.getSkyHeight(), this);
        }
        // Fortschrittsbalken
        int progressBarWidth = getWidth() / 2 - 120;
        int progressBarHeight = 8;
        int barY = 18;
        int progress = (int) ((double) player.getX() / currentLevel.getGoalX() * progressBarWidth);
        g2.setColor(Color.BLACK);
        g2.drawRect(abstand, barY, progressBarWidth, progressBarHeight);
        g2.setColor(Color.WHITE);
        g2.fillRect(abstand, barY, progress, progressBarHeight);
        g2.setColor(new Color(255, 212, 50));
        g2.fillRect(abstand + progressBarWidth - 8, barY, 8, progressBarHeight);

        //Plattformen
        for (Platform platform : currentLevel.getPlatforms()) {
            g2.setColor(platform.getPlatformColor());
            g2.fillRect((platform.getX() - cameraX), platform.getY(), platform.getWidth(),
                    platform.getHeight());
            if (platform instanceof MovingPlatform) {
                ((MovingPlatform) platform).move(currentLevel.getPlatformSpeed());
            } else if (platform instanceof CheckpointPlatform) {
                g2.fillRect(abstand + (platform.getX() * progressBarWidth / currentLevel.getGoalX()), barY, 8, 8);
            }
        }
        for (Entity entity : currentLevel.getEntities()) {
            g2.drawImage(entity.getEntityImage(), entity.getX() - cameraX, entity.getY(), entity.getSize(), entity.getSize(), this);
            entity.move(currentLevel.getPlatformSpeed());
        }
        // Spieler
        g2.drawImage(player.getImage(), player.getX() - cameraX, player.getY(), player.getWidth(), player.getHeight(), this);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(Color.white);
        g2.setColor(Color.WHITE);
        if (player.getName().length() < 5) {
            g2.drawString(player.getName(), player.getX() - cameraX + (5 - player.getName().length()) * 4, player.getY() - 10);
        } else if (player.getName().length() >= 7) {
            g2.drawString(player.getName(), player.getX() - cameraX - (player.getName().length() - 7) * 4, player.getY() - 10);
        } else {
            g2.drawString(player.getName(), player.getX() - cameraX, player.getY() - 10);
        }

        //Level Spezifisch
        if (currentLevel instanceof Level2) {
            g2.drawImage(arrowVertical, 2080 - cameraX, getHeight() / 2, arrowVertical.getWidth(this) * 3 / 7, arrowVertical.getHeight(this) * 3 / 7, this);
        } else if (currentLevel instanceof Level1) {
            g2.drawImage(arrowVertical, 3880 - cameraX, 720, arrowVertical.getWidth(this) / 6, arrowVertical.getHeight(this) / 6, this);
        }
        g2.drawImage(goalFlag, currentLevel.getGoalX() - cameraX, currentLevel.getGoalY() - goalFlag.getHeight(this) + 5, goalFlag.getWidth(this), goalFlag.getHeight(this), this);

        // Steuerungsbilder für Spieler 1 (WASD)
        if (player == player1) {
            g2.drawImage(controlWASD, 85 - cameraX1, 280, controlWASD.getWidth(this) / 4, controlWASD.getHeight(this) / 4, this);
            g2.drawImage(arrowHorizontal, 83 - cameraX1, 367, arrowHorizontal.getWidth(this) / 4, arrowHorizontal.getHeight(this) / 6, this);
        }
        // Steuerungsbilder für Spieler 2 (Pfeiltasten)
        if (player == player2) {
            g2.drawImage(controlArrows, 85 - cameraX2, 280, controlArrows.getWidth(this) / 4, controlArrows.getHeight(this) / 4, this);
            g2.drawImage(arrowHorizontal, 83 - cameraX2, 367, arrowHorizontal.getWidth(this) / 4, arrowHorizontal.getHeight(this) / 6, this);
        }
    }

    public void update() {
        boolean moved = false;
        if (player1.getX() >= currentLevel.getGoalX()) {
            this.winner = player1;
            this.loser = player2;
        } else if (player2.getX() >= currentLevel.getGoalX()) {
            this.winner = player2;
            this.loser = player1;
        }
        if (winner != null) {
            gameTimer.stop();
            finishTime = clock.getElapsedTime();
            finishTimeDisplay = clock.getFormattedTime();
            currentLevel.getBackgroundMusic().stop();
            currentLevel.getWinSound().play();
            Main.startChickenGame();
        }

        // **Steuerung für Spieler 1**
        if (controls.isRight1Pressed()) {
            player1.moveRight(player1.getSpeed());
            moved = true;
        }
        if (controls.isLeft1Pressed()) {
            player1.moveLeft(player1.getSpeed());
            moved = true;
        }
        if (controls.isUp1Pressed()) {
            player1.jump();
            moved = true;
        }

        // **Steuerung für Spieler 2**
        if (controls.isRight2Pressed()) {
            player2.moveRight(player2.getSpeed());
            moved = true;
        }
        if (controls.isLeft2Pressed()) {
            player2.moveLeft(player2.getSpeed());
            moved = true;
        }
        if (controls.isUp2Pressed()) {
            player2.jump();
            moved = true;
        }

        player1.applyGravity(currentLevel);
        player2.applyGravity(currentLevel);
        player1.boostMovement();
        player2.boostMovement();
        player1.checkCollision();
        player2.checkCollision();

        // Stoppuhr starten bei erster Bewegung
        if (!timerStarted && moved) {
            clock.start();
            timerStarted = true;
            currentLevel.getBackgroundMusic().play();
        }
        if (controls.isEscapePressed()) {
            gameTimer.stop();
            currentLevel.getBackgroundMusic().stop();
            Main.showMenu();
        }

        repaint();
    }

    public long getFinishTime() {
        return finishTime;
    }

    public String getFinishTimeDisplay() {
        return finishTimeDisplay;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

}