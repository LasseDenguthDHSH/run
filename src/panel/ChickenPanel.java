package src.panel;

import src.Main;
import src.Clock;
import src.level.ChickenLevel;
import src.player.Player;
import src.player.Controls;

import javax.swing.*;
import java.awt.*;

public class ChickenPanel extends JPanel {
    private Clock clock;
    private ChickenLevel currentLevel;
    private Controls controls;
    private Timer gameTimer;
    private boolean timerStarted = false;
    private JumpPanel jumpPanel;
    private Image chickenImage;
    private Image groundImage;
    private Image skyImage;
    private boolean justShot = false;
    private JButton startButton;
    private Player chicken;
    private Player winner;
    private Player loser;
    private Image[] ammo = new Image[6];
    private Image[] hits = new Image[6];
    private Image hitLetters;
    JPanel buttonPanel;


    public ChickenPanel(JumpPanel jumpPanel) {
        this.jumpPanel = jumpPanel;
        this.currentLevel = new ChickenLevel("ChickenLevel");

        // Load images
        this.chickenImage = new ImageIcon("src/images/chicken.png").getImage();
        this.groundImage = currentLevel.getGroundImage();
        this.skyImage = currentLevel.getSkyImage();

        this.chicken = new Player(jumpPanel.getWinner().getName(), 64, 64, currentLevel, chickenImage);
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

        this.controls = new Controls();
        this.addKeyListener(controls);
        this.addMouseListener(controls);
        this.addMouseMotionListener(controls);
        this.setFocusable(true);

        this.clock = new Clock();
        gameTimer = new Timer(16, e -> update());

        // Initialize button
        startButton = new JButton("Start");


        setLayout(new BorderLayout());
        buttonPanel = new JPanel();
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
            clock.start();
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

        int bulletX = getWidth() / 2 + getWidth() / 4 + 150;
        int bulletY = getHeight() / 2 + getHeight() / 4 - 130;
        switch (currentLevel.getBullets()) {
            case 0 ->
                    g2.drawImage(ammo[0], bulletX, bulletY, ammo[0].getWidth(this) / 2, ammo[0].getHeight(this) / 2, this);
            case 1 ->
                    g2.drawImage(ammo[1], bulletX, bulletY, ammo[1].getWidth(this) / 2, ammo[1].getHeight(this) / 2, this);
            case 2 ->
                    g2.drawImage(ammo[2], bulletX, bulletY, ammo[2].getWidth(this) / 2, ammo[2].getHeight(this) / 2, this);
            case 3 ->
                    g2.drawImage(ammo[3], bulletX, bulletY, ammo[3].getWidth(this) / 2, ammo[3].getHeight(this) / 2, this);
            case 4 ->
                    g2.drawImage(ammo[4], bulletX, bulletY, ammo[4].getWidth(this) / 2, ammo[4].getHeight(this) / 2, this);
            case 5 ->
                    g2.drawImage(ammo[5], bulletX, bulletY, ammo[5].getWidth(this) / 2, ammo[5].getHeight(this) / 2, this);

        }
        int hitsX = getWidth() / 2 - 90;
        int hitsY = 64;
        g2.drawImage(hitLetters, hitsX, hitsY, hitLetters.getWidth(this), hitLetters.getHeight(this), this);
        switch (currentLevel.getHits()) {
            case 0 ->
                    g2.drawImage(hits[0], hitsX + ammo[3].getWidth(this) / 2 - 15, hitsY, hits[0].getWidth(this) * 3 / 4, hits[0].getHeight(this) * 4 / 6, this);
            case 1 ->
                    g2.drawImage(hits[1], hitsX + ammo[3].getWidth(this) / 2 - 15, hitsY, hits[1].getWidth(this) * 3 / 4, hits[1].getHeight(this) * 4 / 6, this);
            case 2 ->
                    g2.drawImage(hits[2], hitsX + ammo[3].getWidth(this) / 2 - 15, hitsY, hits[2].getWidth(this) * 3 / 4, hits[2].getHeight(this) * 4 / 6, this);
            case 3 ->
                    g2.drawImage(hits[3], hitsX + ammo[3].getWidth(this) / 2 - 15, hitsY, hits[3].getWidth(this) * 3 / 4, hits[3].getHeight(this) * 4 / 6, this);
            case 4 ->
                    g2.drawImage(hits[4], hitsX + ammo[3].getWidth(this) / 2 - 15, hitsY, hits[4].getWidth(this) * 3 / 4, hits[4].getHeight(this) * 4 / 6, this);
            case 5 ->
                    g2.drawImage(hits[5], hitsX + ammo[3].getWidth(this) / 2 - 15, hitsY, hits[5].getWidth(this) * 3 / 4, hits[5].getHeight(this) * 4 / 6, this);

        }
        // Draw stopwatch
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 35));
        g2.drawString(clock.getFormattedTime(), getWidth() / 2 - 40, 170);

        if (!timerStarted) {
            g2.drawImage(currentLevel.getInformations(), getWidth() / 2 - 600, 60, currentLevel.getInformations().getWidth(this) * 6 / 5, currentLevel.getInformations().getHeight(this) * 6 / 5, this);
        }
        // Draw chicken player
        g2.drawImage(chicken.getImage(), chicken.getX(), chicken.getY(), chicken.getWidth(), chicken.getHeight(), this);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        if (winner.getName().length() < 5) {
            g2.drawString(winner.getName(), chicken.getX() + (5 - winner.getName().length()) * 4, chicken.getY() - 10);
        } else if (winner.getName().length() >= 7) {
            g2.drawString(winner.getName(), chicken.getX() - (winner.getName().length() - 7) * 4, chicken.getY() - 10);
        } else {
            g2.drawString(winner.getName(), chicken.getX(), chicken.getY() - 10);
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
                this.loser = jumpPanel.getWinner();
            }
            Main.showWinPanel();
        }

        // Player movement
        if (controls.isRight1Pressed()) {
            if (chicken.getX() <= getWidth() - 64) {
                chicken.moveRight(winner.getSpeed());
                moved = true;
            }
        }
        if (controls.isLeft1Pressed()) {
            chicken.moveLeft(chicken.getSpeed());
            moved = true;
        }
        if (controls.isUp1Pressed()) {
            if (chicken.getY() >= 64) {
                chicken.jump();
                moved = true;
            }
        }
        if (controls.isMouseClicked()) {
            if (!justShot) {
                currentLevel.setBullets(currentLevel.getBullets() - 1);
                currentLevel.getGun_sound().play();
                Rectangle hitbox = new Rectangle(chicken.getX(), chicken.getY(), 64, 64);
                if (hitbox.contains(controls.getMouseX(), controls.getMouseY())) {
                    currentLevel.getChicken_sound().play();
                    currentLevel.setHits(currentLevel.getHits() + 1);
                }
                justShot = true;
            }
        } else {
            justShot = false;
        }

        chicken.applyGravity(currentLevel);

        // Start stopwatch when player moves
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

    public ChickenLevel getCurrentLevel() {
        return currentLevel;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }
}