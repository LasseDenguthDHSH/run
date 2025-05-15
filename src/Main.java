package src;

import javax.swing.*;
import java.awt.*;

public class Main {
    static CardLayout cardLayout;
    static JPanel panelManager;
    static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame("Spiel");
        frame.setSize(1600, 837);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Panel Manager
        cardLayout = new CardLayout();
        panelManager = new JPanel(cardLayout);

        // Menüpanel erstellen
        MenuPanel menuPanel = new MenuPanel();
        panelManager.add(menuPanel, "Menu");

        // Hauptpanel zum Frame hinzufügen
        frame.add(panelManager);
        frame.setVisible(true);
    }

    public static void startGame(Level level) {
        // Spielpanel erstellen und hinzufügen
        GamePanel gamePanel = new GamePanel(level);
        panelManager.add(gamePanel, "Game");

        // Zum Spielpanel wechseln
        cardLayout.show(panelManager, "Game");
        gamePanel.requestFocusInWindow();
    }

    public static void showMenu() {
        // Zum Menüpanel wechseln
        cardLayout.show(panelManager, "Menu");
    }
}
