package src.panel;

import src.Main;
import src.Stoppuhr;
import src.level.ChickenLevel;
import src.level.Level;
import src.player.Player;
import src.player.Steuerung;

import javax.swing.*;
import java.awt.*;

public class ChickenPanel extends JPanel {
    private Stoppuhr stoppuhr;
    private Player chicken;
    private ChickenLevel currentLevel;
    private Steuerung steuerung;
    private Timer gameTimer;
    private boolean timerStarted = false;
    private JumpPanel jumpPanel;
    private Image chickenImage;
    private Image groundImage;
    private Image skyImage;
    private boolean justShot = false;

    public ChickenPanel(JFrame frame, JumpPanel jumpPanel) {
        this.jumpPanel = jumpPanel;
        this.currentLevel = new ChickenLevel("ChickenLevel");

        // Load images
        this.chickenImage = new ImageIcon("src/images/chicken.png").getImage();
        this.groundImage = currentLevel.getGroundImage();
        this.skyImage = currentLevel.getSkyImage();

        this.chicken = new Player("Chicken", 64, 64, currentLevel, chickenImage);

        this.steuerung = new Steuerung();
        this.addKeyListener(steuerung);
        this.addMouseListener(steuerung);
        this.addMouseMotionListener(steuerung);
        this.setFocusable(true);

        this.stoppuhr = new Stoppuhr();
        gameTimer = new Timer(16, e -> update());
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw background
        g2.drawImage(skyImage, 0, 0, getWidth(), currentLevel.getSkyHeight(), this);
        g2.drawImage(groundImage, 0, currentLevel.getGroundY(), getWidth(), 100, this);

        // Draw chicken player
        g2.drawImage(chicken.getImage(), chicken.getX(), chicken.getY(), chicken.getWidth(), chicken.getHeight(), this);

        // Draw stopwatch
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(stoppuhr.getFormattedTime(), getWidth() / 2 - 31, 32);

        // Draw Bullet Counter
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(""+ currentLevel.getBullets(), 50, 50);

        //Draw Hits Counter
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(""+ currentLevel.getHits(), 120, 50);
    }

    public void update() {
        boolean moved = false;
        if (currentLevel.getBullets() == 0) {
            if (currentLevel.getHits() >= 4) {
                jumpPanel.setWinner(jumpPanel.getLoser());
            }
            Main.showWinPanel(jumpPanel);
        }
        // Player movement
        if (steuerung.isRight1Pressed()) {

            chicken.moveRight(chicken.getSpeed());
            moved = true;
        }
        if (steuerung.isLeft1Pressed()) {
            chicken.moveLeft(chicken.getSpeed());
            moved = true;
        }
        if (steuerung.isUp1Pressed()) {
            chicken.jump();
            moved = true;
        }
        if (steuerung.isMouseClicked()){
            System.out.println("clicked");
            if (!justShot) {
                currentLevel.setBullets(currentLevel.getBullets()-1);
                Rectangle hitbox = new Rectangle(chicken.getX(), chicken.getY(), 64, 64);
                System.out.println(steuerung.getMouseX());
                if (hitbox.contains(steuerung.getMouseX(), steuerung.getMouseY())) {
                    currentLevel.setHits(currentLevel.getHits() + 1);
                    System.out.println("Hit!");
                }
                justShot = true;
            }
        }else {
            justShot = false;
        }

        if (steuerung.isSpezialButtonPressed()) {
            Main.showWinPanel(jumpPanel);
        }

        chicken.applyGravity(currentLevel);

        // Start stopwatch when player moves
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

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
