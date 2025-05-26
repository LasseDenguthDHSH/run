package src;

import src.level.Level;
import src.panel.GamePanel;
import src.panel.MenuPanel;
import src.panel.WinPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    static CardLayout cardLayout;
    static JPanel panelManager;
    static JFrame frame;
    static int fensterLeiste = 37;
    static GamePanel gamePanel;

    public static void main(String[] args) {

        frame = new JFrame();
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
        gamePanel = new GamePanel(level, frame);
        panelManager.add(gamePanel, level.getTitle());
        frame.setTitle(level.getTitle());

        // Zum Spielpanel wechseln
        cardLayout.show(panelManager, level.getTitle());
        gamePanel.requestFocusInWindow();
    }

    public static void showMenu() {
        MenuPanel menuPanel = new MenuPanel();
        panelManager.add(menuPanel, "Menu");
        cardLayout.show(panelManager, "Menu");
        menuPanel.requestFocusInWindow();
        frame.setTitle("Menu");
    }

    public static void showWinPanel() {
        WinPanel winPanel = new WinPanel(gamePanel);
        panelManager.add(winPanel, "Winner");
        cardLayout.show(panelManager, "Winner");
        winPanel.requestFocusInWindow();
        frame.setTitle("Winner");
    }
}