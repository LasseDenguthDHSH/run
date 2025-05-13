package src;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Level-Instanzen erstellen
        Level1 level1 = new Level1();
        Level2 level2 = new Level2();
        Level3 level3 = new Level3();

        Scanner sc = new Scanner(System.in);
        String auswahl = sc.nextLine();

        Level startLevel;

        if (auswahl.equals("1")) {
            startLevel = level1;
        } else if (auswahl.equals("2")) {
            startLevel = level2;
        } else if (auswahl.equals("3")) {
            startLevel = level3;
        } else {
            System.out.println("Ungültige Auswahl! Das Spiel wird nicht gestartet.");
            return;
        }

        // Fenster erstellen und Spiel starten
        JFrame frame = new JFrame("Spiel");
        GamePanel gamePanel = new GamePanel(startLevel);
        frame.add(gamePanel);
        frame.setSize(1600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gamePanel.requestFocusInWindow();
    }
}