package src.panel;

import src.player.Player;

import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {
    GamePanel gamePanel;
    Player winner, loser;
    Image winnerImage, loserImage;
    ImageIcon rainCloudGif;

    public WinPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.winner = gamePanel.getWinner();
        this.loser = (gamePanel.getPlayer1() == winner) ? gamePanel.getPlayer2() : gamePanel.getPlayer1();

        // Bilder laden (Pfad anpassen!)
        winnerImage = winner.getImage();
        loserImage = loser.getImage();
        rainCloudGif = new ImageIcon("src/images/raincloud.gif"); // GIF der Regenwolke
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Hintergrund-Farbverlauf
        GradientPaint gradient = new GradientPaint(0, 0, Color.GRAY, getWidth(), getHeight(), Color.LIGHT_GRAY);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Gewinnertext
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        String winnerText = "Winner: " + winner.getName();
        int textWidth = g2.getFontMetrics().stringWidth(winnerText);
        int textX = (getWidth() - textWidth) / 2;
        int textY = getHeight() / 2;
        g2.drawString(winnerText, textX, textY);

        // Krone über Gewinnernamen
        g2.setColor(Color.YELLOW);
        int crownX = textX + textWidth / 2 - 25;
        int crownY = textY - 60;
        int[] xPoints = {crownX, crownX + 10, crownX + 20, crownX + 30, crownX + 40};
        int[] yPoints = {crownY + 30, crownY, crownY + 20, crownY, crownY + 30};
        g2.fillPolygon(xPoints, yPoints, xPoints.length);

        // Gewinnerbild unter Krone
        if (winnerImage != null) {
            g2.drawImage(winnerImage, textX + textWidth / 2 - 50, textY + 20, 100, 100, this);
        }

        // GIF-Regenwolke
        if (rainCloudGif != null) {
            rainCloudGif.paintIcon(this, g, 50, 50);
}

            // Verliererbild unter der Wolke
        if (loserImage != null) {
            g2.drawImage(loserImage, 100, 160, 100, 100, this);
        }
    }
}
