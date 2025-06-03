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
    private MenuPanel menuPanel = Main.menuPanel;
    private JButton startButton;
    private Player winner;
    private Player loser;
    private Image [] ammo = new Image[6];
    private Image [] hits = new Image[6];
    private Image hitLetters;

    public ChickenPanel(JFrame frame, JumpPanel jumpPanel) {
        this.jumpPanel = jumpPanel;
        this.currentLevel = new ChickenLevel("ChickenLevel");

        // Load images
        this.chickenImage = new ImageIcon("src/images/chicken.png").getImage();
        this.groundImage = currentLevel.getGroundImage();
        this.skyImage = currentLevel.getSkyImage();

        this.chicken = new Player("Chicken", 64, 64, currentLevel, chickenImage);
        this.winner = jumpPanel.getWinner();
        this.loser = jumpPanel.getLoser();

        this.ammo[0] = new ImageIcon("src/images/ammo0.png").getImage();
        this.ammo[1] = new ImageIcon("src/images/ammo1.png").getImage();
        this.ammo[2] = new ImageIcon("src/images/ammo2.png").getImage();
        this.ammo[3] = new ImageIcon("src/images/ammo3.png").getImage();
        this.ammo[4] = new ImageIcon("src/images/ammo4.png").getImage();
        this.ammo[5] = new ImageIcon("src/images/ammo5.png").getImage();

        this.hitLetters = new ImageIcon("src/images/hits.png").getImage();
        this.hits[0] = new ImageIcon("src/images/hits0.png").getImage();
        this.hits[1] = new ImageIcon("src/images/hits1.png").getImage();
        this.hits[2] = new ImageIcon("src/images/hits2.png").getImage();
        this.hits[3] = new ImageIcon("src/images/hits3.png").getImage();
        this.hits[4] = new ImageIcon("src/images/hits4.png").getImage();
        this.hits[5] = new ImageIcon("src/images/hits5.png").getImage();

        this.steuerung = new Steuerung();
        this.addKeyListener(steuerung);
        this.addMouseListener(steuerung);
        this.addMouseMotionListener(steuerung);
        this.setFocusable(true);

        this.stoppuhr = new Stoppuhr();
        gameTimer = new Timer(16, e -> update());

        // Initialize button
        startButton = new JButton("Start");


        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(startButton, gbc);
        buttonPanel.add(startButton);

        this.add(buttonPanel);
        startButton.addActionListener(e -> {
            timerStarted = true;
            gameTimer.start();
            stoppuhr.start();
            startButton.setEnabled(false);
            this.remove(buttonPanel);
        });
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

        int bulletX = 40;
        int bulletY = 680;
        switch (currentLevel.getBullets()){
            case 0 -> g2.drawImage(ammo[0], bulletX, bulletY, ammo[0].getWidth(this)/2, ammo[0].getHeight(this)/2, this);
            case 1 -> g2.drawImage(ammo[1], bulletX, bulletY, ammo[1].getWidth(this)/2, ammo[1].getHeight(this)/2, this);
            case 2 -> g2.drawImage(ammo[2], bulletX, bulletY, ammo[2].getWidth(this)/2, ammo[2].getHeight(this)/2, this);
            case 3 -> g2.drawImage(ammo[3], bulletX, bulletY, ammo[3].getWidth(this)/2, ammo[3].getHeight(this)/2, this);
            case 4 -> g2.drawImage(ammo[4], bulletX, bulletY, ammo[4].getWidth(this)/2, ammo[4].getHeight(this)/2, this);
            case 5 -> g2.drawImage(ammo[5], bulletX, bulletY, ammo[5].getWidth(this)/2, ammo[5].getHeight(this)/2, this);

        }
        // Draw stopwatch
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(stoppuhr.getFormattedTime(), getWidth() / 2 - 31, 32);

        // Draw Hits Counter

        g2.drawImage(hitLetters, bulletX , bulletY- 50, hitLetters.getWidth(this), hitLetters.getHeight(this), this);
        switch (currentLevel.getHits()){
            case 0 -> g2.drawImage(hits[0], bulletX + 150, bulletY - 50, hits[0].getWidth(this), hits[0].getHeight(this)/2, this);
            case 1 -> g2.drawImage(hits[1], bulletX + 150, bulletY - 50, hits[1].getWidth(this), hits[1].getHeight(this)/2, this);
            case 2 -> g2.drawImage(hits[2], bulletX + 150, bulletY - 50, hits[2].getWidth(this), hits[2].getHeight(this)/2, this);
            case 3 -> g2.drawImage(hits[3], bulletX + 150, bulletY - 50, hits[3].getWidth(this), hits[3].getHeight(this)/2, this);
            case 4 -> g2.drawImage(hits[4], bulletX + 150, bulletY - 50, hits[4].getWidth(this), hits[4].getHeight(this)/2, this);
            case 5 -> g2.drawImage(hits[5], bulletX + 150, bulletY - 50, hits[5].getWidth(this), hits[5].getHeight(this)/2, this);

        }
    }

    public void update() {
        if (startButton.isEnabled()) {
            return;
        }

        boolean moved = false;
        if (currentLevel.getBullets() == 0) {
            if (currentLevel.getHits() >= 3) {
                this.winner = jumpPanel.getLoser();
                this.loser  = jumpPanel.getWinner();
            }
            Main.showWinPanel(this);
        }

        // Player movement
        if (steuerung.isRight1Pressed()) {
            if(chicken.getX()<= getWidth() - 64){
                chicken.moveRight(chicken.getSpeed());
                moved = true;
            }
        }
        if (steuerung.isLeft1Pressed()) {
            chicken.moveLeft(chicken.getSpeed());
            moved = true;
        }
        if (steuerung.isUp1Pressed()) {
            if (chicken.getY() >= 64){
                chicken.jump();
                moved = true;
            }
        }
        if (steuerung.isMouseClicked()) {
            if (!justShot) {
                currentLevel.setBullets(currentLevel.getBullets() - 1);
                Rectangle hitbox = new Rectangle(chicken.getX(), chicken.getY(), 64, 64);
                if (hitbox.contains(steuerung.getMouseX(), steuerung.getMouseY())) {
                    currentLevel.setHits(currentLevel.getHits() + 1);
                }
                justShot = true;
            }
        } else {
            justShot = false;
        }

        if (steuerung.isSpezialButtonPressed()) {
            Main.showWinPanel(this);
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

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }
}