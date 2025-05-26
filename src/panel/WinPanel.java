package src.panel;
import javax.swing.*;
import java.awt.*;


public class WinPanel extends JPanel{
    GamePanel gamePanel;
    public WinPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        g2.drawString(gamePanel.getEndZeit(), getWidth()/2-31, 32);
    }
}
