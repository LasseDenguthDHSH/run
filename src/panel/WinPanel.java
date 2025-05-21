package src.panel;
import javax.swing.*;
import java.awt.*;


public class WinPanel extends JPanel{

    public WinPanel() {

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setBackground(Color.BLUE);
    }
}
