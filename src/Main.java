package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private GamePanel gamePanel;
    private Steuerung steuerung;

    public Main() {
        int frameWidth = 1600;
        int frameHeight = frameWidth / 2;

        setTitle("Level: Mittel");
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        steuerung = new Steuerung();
        gamePanel = new GamePanel(frameWidth, frameHeight, steuerung);
        add(gamePanel);

        addKeyListener(steuerung);
        setVisible(true);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.update();
                gamePanel.repaint();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Main();
    }
}





