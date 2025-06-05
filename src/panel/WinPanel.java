package src.panel;

import src.Main;
import src.player.Player;

import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {
    ChickenPanel chickenPanel;
    Player winner, loser;
    Image winnerImage, loserImage;
    ImageIcon rainCloudGif;
    JButton playAgain;
    JPanel buttonPanel;

    public WinPanel(ChickenPanel chickenPanel) {
        this.chickenPanel = chickenPanel;
        this.winner = chickenPanel.getWinner();
        this.loser = chickenPanel.getLoser();

        winnerImage = winner.getImage();
        loserImage = loser.getImage();
        rainCloudGif = new ImageIcon("src/images/raincloud.gif"); // GIF der Regenwolke

        playAgain = new JButton("Start");
        buttonPanel = new JPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(playAgain, gbc);
        buttonPanel.add(playAgain);
        playAgain.addActionListener(e -> {
            Main.showMenu();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Hintergrund-Farbverlauf
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, getWidth(), getHeight(), Color.YELLOW);
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

        if (winnerImage != null) {
            g2.drawImage(winnerImage, getWidth()-164, 400, 64, 64, this);
        }

        // GIF-Regenwolke
        if (rainCloudGif != null) {
            rainCloudGif.paintIcon(this, g, -20, 75);
        }

        // Verliererbild unter der Wolke
        if (loserImage != null) {
            g2.drawImage(loserImage, 132, 400, 64, 64, this);
        }
    }
}
