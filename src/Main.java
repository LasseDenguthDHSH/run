package src;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame("Spiel");
        frame.setSize(1600, 839);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Menüpanel erstellen
        MenuPanel menuPanel = new MenuPanel();
        mainPanel.add(menuPanel, "Menu");

        // Hauptpanel zum Frame hinzufügen
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void startGame(Level level) {
        // Spielpanel erstellen und hinzufügen
        GamePanel gamePanel = new GamePanel(level);
        mainPanel.add(gamePanel, "Game");

        // Zum Spielpanel wechseln
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
    }

    public static void showMenu() {
        // Zum Menüpanel wechseln
        cardLayout.show(mainPanel, "Menu");
    }
}
