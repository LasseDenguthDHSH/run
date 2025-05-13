package src;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Player player1;
    private Player player2;
    private int frameWidth;
    private int frameHeight;
    private int ground1Y;
    private int ground2Y;
    private Image lavaImage;
    private Image player1Image;
    private Image player2Image;
    private Steuerung steuerung;

    public GamePanel(int frameWidth, int frameHeight, Steuerung steuerung) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.ground1Y = frameHeight / 2 - 50 - 15;
        this.ground2Y = frameHeight - 75 -15;
        this.player1 = new Player(32, 32, 50, ground1Y - 32, 5);
        this.player2 = new Player(32, 32, 50, ground2Y-32, 5);
        this.lavaImage = new ImageIcon("src/images/Unbenannt.png").getImage();
        this.player1Image = new ImageIcon("src/images/Player1.png").getImage();
        this.player2Image = new ImageIcon("src/images/Player2.png").getImage();
        this.steuerung = steuerung;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw Background
        g2.drawImage(lavaImage, 0, ground1Y, frameWidth, 50, this);
        g2.drawImage(lavaImage, 0, ground2Y, frameWidth, 50, this);
        g2.setColor(Color.black);
        g2.fillRect(0, frameHeight / 2 - 7 -15, frameWidth, 14);

        // Draw the players
        g2.drawImage(player1Image, player1.x, player1.y, player1.width, player1.height, this);
        g2.drawImage(player2Image, player2.x, player2.y, player2.width, player2.height, this);
    }

    public void update() {
        // src.src.Player 1 jump logic
        if (steuerung.up1Pressed && !player1.isJumping) {
            player1.isJumping = true;
            player1.velocityY = -player1.jumpStrength;
        }

        // Apply gravity
        if (player1.isJumping) {
            player1.y += player1.velocityY;
            player1.velocityY += player1.gravity;
            if (player1.y >= ground1Y - player1.height) {
                player1.y = ground1Y - player1.height;
                player1.isJumping = false;
                player1.velocityY = 0;
            }
        }

        // src.src.Player 1 horizontal movement
        if (steuerung.right1Pressed) {
            player1.x += player1.speed;
        }
        if (steuerung.left1Pressed) {
            player1.x -= player1.speed;
        }

        // src.src.Player 2 jump logic
        if (steuerung.up2Pressed && !player2.isJumping) {
            player2.isJumping = true;
            player2.velocityY = -player2.jumpStrength;
        }

        // Apply gravity
        if (player2.isJumping) {
            player2.y += player2.velocityY;
            player2.velocityY += player2.gravity;
            if (player2.y >= ground2Y - player2.height) {
                player2.y = ground2Y - player2.height;
                player2.isJumping = false;
                player2.velocityY = 0;
            }
        }

        // src.src.Player 2 horizontal movement
        if (steuerung.right2Pressed) {
            player2.x += player2.speed;
        }
        if (steuerung.left2Pressed) {
            player2.x -= player2.speed;
        }
    }
}

