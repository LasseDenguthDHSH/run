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

        playAgain = new JButton("Nochmal Spielen");
        playAgain.setVisible(true); // Sicherstellen, dass der Button sichtbar ist
        buttonPanel = new JPanel();
        buttonPanel.add(playAgain);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1; // Änderung: Button unterhalb des Gewinnertextes
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0); // Abstand nach oben hinzufügen

        this.add(buttonPanel, gbc); // ButtonPanel hinzufügen
        playAgain.addActionListener(e -> Main.showMenu());
        Main.chickenPanel.setWinner(null);
        Main.chickenPanel.setLoser(null);
        Main.chickenPanel.getCurrentLevel().setBullets(5);
        Main.chickenPanel.getCurrentLevel().setHits(0);

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
        int textY = getHeight() / 3; // Änderung: Etwas höher setzen
        g2.drawString(winnerText, textX, textY);

        g2.setFont(new Font("Arial", Font.BOLD, 18));
        if (winnerImage != null) {
            g2.drawImage(winnerImage, getWidth() - 164, 400, 64, 64, this);
            g2.drawString(winner.getName(), getWidth() - 164, 390);
        }

        if (loserImage != null) {
            g2.drawImage(loserImage, 132, 400, 64, 64, this);
            g2.drawString(loser.getName(), 132, 385);
        }

        if (rainCloudGif != null) {
            rainCloudGif.paintIcon(this, g, -85, 160);
        }
    }
}