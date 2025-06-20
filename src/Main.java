package src;

import src.level.Level;
import src.panel.ChickenPanel;
import src.panel.JumpPanel;
import src.panel.MenuPanel;
import src.panel.WinPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static final int fensterLeiste = 37;
    private static CardLayout cardLayout;
    private static JPanel panelManager;
    private static JFrame frame;
    public static JumpPanel jumpPanel;
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
        jumpPanel = new JumpPanel(level, frame);
        panelManager.add(jumpPanel, level.getTitle());
        frame.setTitle(level.getTitle());

        cardLayout.show(panelManager, level.getTitle());
        jumpPanel.requestFocusInWindow();
    }

    public static void showMenu() {
        menuPanel = new MenuPanel();
        panelManager.add(menuPanel, "Menu");
        frame.setTitle("Menu");

        cardLayout.show(panelManager, "Menu");
        menuPanel.requestFocusInWindow();
    }

    public static void showWinPanel() {
        winPanel = new WinPanel(chickenPanel, jumpPanel);
        panelManager.add(winPanel, "Winner");
        frame.setTitle("Winner");

        cardLayout.show(panelManager, "Winner");
        winPanel.requestFocusInWindow();
    }

    public static void startChickenGame() {
        chickenPanel = new ChickenPanel(jumpPanel);
        panelManager.add(chickenPanel, chickenPanel.getCurrentLevel().getTitle());
        frame.setTitle(chickenPanel.getCurrentLevel().getTitle());

        cardLayout.show(panelManager, chickenPanel.getCurrentLevel().getTitle());
        chickenPanel.requestFocusInWindow();




    }
}