package src;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Player player1;
    private Player player2;
    private Level currentLevel;
    private Steuerung steuerung;
    private Image backgroundImage;
    private Timer gameTimer;

    public GamePanel(Level level) {
        this.currentLevel = level;
        this.currentLevel.setupLevel();

        this.steuerung = new Steuerung(this);
        this.addKeyListener(steuerung);
        this.setFocusable(true);

        this.player1 = new Player(32, 32, currentLevel.getPlayer1StartX(), currentLevel.getPlayer1StartY(), 5, currentLevel.getPlayer1ImagePath());
        this.player2 = new Player(32, 32, currentLevel.getPlayer2StartX(), currentLevel.getPlayer2StartY(), 5, currentLevel.getPlayer2ImagePath());

        this.backgroundImage = new ImageIcon(currentLevel.getBackgroundImage()).getImage();

        gameTimer = new Timer(16, e -> update());
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(backgroundImage, 0, currentLevel.getGroundY(), getWidth(), 50, this);
        g2.drawImage(player1.getImage(), player1.x, player1.y, player1.width, player1.height, this);
        g2.drawImage(player2.getImage(), player2.x, player2.y, player2.width, player2.height, this);
    }

    public void update() {
        if (steuerung.isUp1Pressed() && !player1.isJumping) {
            player1.jump();
        }
        if (steuerung.isRight1Pressed()) {
            player1.moveRight();
        }
        if (steuerung.isLeft1Pressed()) {
            player1.moveLeft();
        }
        player1.applyGravity();

        if (steuerung.isUp2Pressed() && !player2.isJumping) {
            player2.jump();
        }
        if (steuerung.isRight2Pressed()) {
            player2.moveRight();
        }
        if (steuerung.isLeft2Pressed()) {
            player2.moveLeft();
        }
        player2.applyGravity();

        repaint();
    }
}