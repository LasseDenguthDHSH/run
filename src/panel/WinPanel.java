package src.panel;

import src.Main;
import src.network.DatabaseManager;
import src.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class WinPanel extends JPanel {
    ChickenPanel chickenPanel;
    JumpPanel jumpPanel;
    Player winner, loser;
    Image winnerImage, loserImage;
    ImageIcon rainCloudGif;
    JButton playAgain;
    JPanel buttonPanel;

    public WinPanel(ChickenPanel chickenPanel, JumpPanel jumpPanel) {
        this.chickenPanel = chickenPanel;
        this.jumpPanel = jumpPanel;
        this.winner = chickenPanel.getWinner();
        this.loser = chickenPanel.getLoser();
        DatabaseManager.saveSpielzeit(winner.getName(), jumpPanel.getFinishTime(),
                    jumpPanel.getFinishTimeDisplay(),jumpPanel.getCurrentLevel());
        DatabaseManager.getSpielzeiten();


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
        int textY = getHeight() / 3 + 50;
        g2.drawString(winnerText, textX, textY);

        //Zeittext
        g2.drawString(jumpPanel.getFinishTimeDisplay(), textX + 100, textY-200);

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
        g2.setColor(Color.WHITE);
        g2.drawString("Bestenliste für " + jumpPanel.getCurrentLevel().getTitle() + ":", textX + 65, textY+235);

        int index = 1;
        Map<String, String> bestenliste = DatabaseManager.getBestenliste(jumpPanel.getCurrentLevel());
        for (Map.Entry<String, String> entry : bestenliste.entrySet()) {
            if (index <= 10) {
                String text = index + ". " + entry.getKey() + ": " + entry.getValue();
                g2.drawString(text, textX + 65, textY + 250 + index *20);
                index++;
            } else {
                break;
            }

        }
    }
}