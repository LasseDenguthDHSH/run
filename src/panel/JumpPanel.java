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
    private Player player1;
    private Player player2;
    private Level currentLevel;
    private Steuerung steuerung;
    private Timer gameTimer;
    private int cameraX1;
    private int cameraX2;
    private Stoppuhr stoppuhr;
    private boolean timerStarted = false;
    private Image arrowVertical;
    private Image arrowHorizontal;
    private Image goalFlag;
    int anzahlHintergrunde;
    int totalWidth;
    Player winner;
    Player loser;
    String endZeit;
    JumpPanel jumpPanel;

    public JumpPanel(Level level, JFrame frame, JumpPanel jumpPanel) {
        this.jumpPanel = jumpPanel;
        this.currentLevel = level;
        this.steuerung = new Steuerung();
        this.addKeyListener(steuerung);
        this.setFocusable(true);

        this.anzahlHintergrunde = currentLevel.getZielX()/frame.getWidth() + 2;
        this.totalWidth = frame.getWidth()*anzahlHintergrunde;

        this.player1 = new Player("Player 1", 32, 32, currentLevel, currentLevel.getPlayer1Image());
        this.player2 = new Player("Player 2", 32, 32, currentLevel, currentLevel.getPlayer2Image());

        this.arrowVertical = new ImageIcon("src/images/arrowVertical.png").getImage();
        this.arrowHorizontal = new ImageIcon("src/images/arrowHorizontal.png").getImage();
        this.goalFlag = new ImageIcon("src/images/goalflag.png").getImage();
        this.stoppuhr = new Stoppuhr();

        gameTimer = new Timer(16, e -> update());
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Linke Hälfte für Spieler 1
        g2.setClip(0, 0, getWidth() / 2, getHeight());
        renderScene(g2, player1, cameraX1, 60, steuerung);

        // Rechte Hälfte für Spieler 2
        g2.setClip(getWidth() / 2, 0, getWidth() / 2, getHeight());
        renderScene(g2, player2, cameraX2, getWidth() / 2 + 60, steuerung);

        // Split
        g2.setClip(null);
        g2.setColor(Color.white);
        g2.drawLine(getWidth() / 2, 58, getWidth() / 2, getHeight());
        // Stoppuhr
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(stoppuhr.getFormattedTime(), getWidth() / 2-31, 32);
    }

    private void renderScene(Graphics2D g2, Player player, int cameraX, int abstand, Steuerung steuerung) {
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
        for (int i = 0; i < anzahlHintergrunde; i += 2) {
            g2.drawImage(currentLevel.getGroundImage(), -cameraX + getWidth() * i, currentLevel.getGroundY(), getWidth() * 2, 100, this);
            g2.drawImage(currentLevel.getSkyImage(), -cameraX + getWidth() * i, 0, getWidth() * 2, currentLevel.getSkyHeight(), this);
        }
        // Fortschrittsbalken
        int progressBarWidth = getWidth() / 2 - 120;
        int progressBarHeight = 8;
        int barY = 18;
        int progress = (int) ((double) player.getX() / currentLevel.getZielX() * progressBarWidth);
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
            if (platform instanceof MovingPlatform){
                platform.move(currentLevel.getPlatformSpeed());
            } else if (platform instanceof CheckpointPlatform) {
                g2.fillRect(abstand + (platform.getX() * progressBarWidth / currentLevel.getZielX()), barY, 8, 8);
            }
        }
        for (Entity entity : currentLevel.getEntities()) {
            g2.drawImage(entity.getEntityImage(), entity.getX()-cameraX, entity.getY(), entity.getSize(), entity.getSize(), this);
            entity.move(currentLevel.getPlatformSpeed());
        }
        // Spieler
        g2.drawImage(player.getImage(), player.getX() - cameraX, player.getY(), player.getWidth(), player.getHeight(), this);

        //Level Spezifisch
        if (currentLevel instanceof Level2) {
            g2.drawImage(arrowVertical, 2080 - cameraX, getHeight() / 2, arrowVertical.getWidth(this) * 3 / 7, arrowVertical.getHeight(this) * 3 / 7, this);
        } else if (currentLevel instanceof Level1) {
            g2.drawImage(arrowVertical, 3880 - cameraX, 720, arrowVertical.getWidth(this) /6, arrowVertical.getHeight(this) / 6, this);
        }
        g2.drawImage(goalFlag, currentLevel.getZielX() - cameraX, currentLevel.getZielY() - goalFlag.getHeight(this)+5, goalFlag.getWidth(this), goalFlag.getHeight(this), this);

        // Steuerungsbilder für Spieler 1 (WASD)
        if (player == player1) {
            g2.drawImage(steuerung.getControlWASD(), 85 - cameraX1, 280, steuerung.getControlWASD().getWidth(this) / 4, steuerung.getControlWASD().getHeight(this) / 4, this);
            g2.drawImage(arrowHorizontal, 83 - cameraX1, 367, arrowHorizontal.getWidth(this) / 4, arrowHorizontal.getHeight(this) / 6, this);
        }
        // Steuerungsbilder für Spieler 2 (Pfeiltasten)
        if (player == player2) {
            g2.drawImage(steuerung.getControlArrows(), 85 - cameraX2, 280, steuerung.getControlArrows().getWidth(this) / 4, steuerung.getControlArrows().getHeight(this) / 4, this);
            g2.drawImage(arrowHorizontal, 83 - cameraX2, 367, arrowHorizontal.getWidth(this) / 4, arrowHorizontal.getHeight(this) / 6, this);
        }
    }

    public void update() {
        boolean moved = false;
        if (player1.getX()>= currentLevel.getZielX()){
            this.winner = player1;
            this.loser = player2;
        } else if (player2.getX()>= currentLevel.getZielX()){
            this.winner = player2;
            this.loser = player1;
        }
        if (winner != null){
            gameTimer.stop();
            endZeit = stoppuhr.getFormattedTime();
            currentLevel.getBackgroundMusic().stop();
            currentLevel.getWinSound().play();
            Main.startChickenGame();
        }

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

        if (steuerung.isSpezialButtonPressed()){
                this.winner = player1;
                this.loser = player2;
            Main.startChickenGame();
        }

        player1.applyGravity(currentLevel);
        player2.applyGravity(currentLevel);
        player1.boostMovement();
        player2.boostMovement();
        checkCollision(player1);
        checkCollision(player2);

        // Stoppuhr starten bei erster Bewegung
        if (!timerStarted && moved) {
            stoppuhr.start();
            timerStarted = true;
            currentLevel.getBackgroundMusic().play();
        }
        if (steuerung.isEscapePressed()) {
            gameTimer.stop();
            currentLevel.getBackgroundMusic().stop();
            Main.showMenu();
        }

        repaint();
    }
    public int getTotalWidth() {
        return totalWidth;
    }

    public String getEndZeit() {
        return endZeit;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public Player getLoser(){
        return loser;
    }
    public void setLoser(Player loser){
        this.loser = loser;
    }
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    public JumpPanel getPanel(){
        return this;
    }
    private void checkCollision(Player player) {
        for (Entity entity : currentLevel.getEntities()) {
            if (player.getX() < entity.getX() + entity.getSize() &&
                    player.getX() + player.getWidth() > entity.getX() &&
                    player.getY() < entity.getY() + entity.getSize() &&
                    player.getY() + player.getHeight() > entity.getY()) {

                // Respawn am letzten Checkpoint oder Startposition
                player.resetToCheckpoint(currentLevel, currentLevel.getRespawnSound());
            }
        }
    }
}