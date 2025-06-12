package src;

import src.level.ChickenLevel;
import src.level.Level;
import src.panel.ChickenPanel;
import src.panel.JumpPanel;
import src.panel.MenuPanel;
import src.panel.WinPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    static CardLayout cardLayout;
    static JPanel panelManager;
    static JFrame frame;
    static int fensterLeiste = 37;
    static JumpPanel jumpPanel;
    public static ChickenPanel chickenPanel;
    public static MenuPanel menuPanel;
    public static WinPanel winPanel;

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
        // Hauptpanel zum Frame hinzdufügen
        frame.add(panelManager);
        frame.setVisible(true);
    }

    public static void startGame(Level level) {
        // Spielpanel erstellen und hinzufügen
        jumpPanel = new JumpPanel(level, frame, jumpPanel);
        panelManager.add(jumpPanel, level.getTitle());
        frame.setTitle(level.getTitle());

        // Zum Spielpanel wechseln
        cardLayout.show(panelManager, level.getTitle());
        jumpPanel.requestFocusInWindow();
    }

    public static void showMenu() {
        menuPanel = new MenuPanel();
        panelManager.add(menuPanel, "Menu");
        cardLayout.show(panelManager, "Menu");
        menuPanel.requestFocusInWindow();
        frame.setTitle("Menu");
    }

    public static void showWinPanel() {
        winPanel = new WinPanel(chickenPanel, jumpPanel);
        panelManager.add(winPanel, "Winner");
        cardLayout.show(panelManager, "Winner");
        winPanel.requestFocusInWindow();
        frame.setTitle("Winner");
        //moin
    }
    public static void startChickenGame() {
        chickenPanel = new ChickenPanel(frame, jumpPanel);
        panelManager.add(chickenPanel, chickenPanel.getCurrentLevel().getTitle());
        frame.setTitle(chickenPanel.getCurrentLevel().getTitle());

        // Zum Spielpanel wechseln
        cardLayout.show(panelManager, chickenPanel.getCurrentLevel().getTitle());
        chickenPanel.requestFocusInWindow();


    }
}