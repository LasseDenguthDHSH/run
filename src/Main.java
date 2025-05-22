package src;

import src.level.Level;
import src.panel.GamePanel;
import src.panel.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    static CardLayout cardLayout;
    static JPanel panelManager;
    static JFrame frame;
    static int fensterLeiste = 37;

    public static void main(String[] args) {

        frame = new JFrame("Spiel");
        frame.setSize(1600, 800 + fensterLeiste);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Panel Manager
        cardLayout = new CardLayout();
        panelManager = new JPanel(cardLayout);

        showMenu();
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
        MenuPanel menuPanel = new MenuPanel();
        panelManager.add(menuPanel, "Menu");
        cardLayout.show(panelManager, "Menu");
        menuPanel.requestFocusInWindow();
    }
}
