package src.panel;

import src.level.Level1;
import src.level.Level2;
import src.level.Level3;
import src.Main;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(39, 78, 99)); // Hintergrundfarbe setzen

        // Titeltext hinzufügen
        ImageIcon titleIcon = new ImageIcon("src/images/JumpAndRunAndMoor.png");
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(140, 0, 0, 0)); // Abstand hinzufügen
        add(titleLabel, BorderLayout.NORTH);


        // Panel für die Level-Schaltflächen
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 70)); // Buttons nebeneinander mit Abstand
        buttonPanel.setBackground(new Color(39, 78, 99));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Abstand zu den Rändern

        // Schaltflächen erstellen
        JButton level1Button = createButton("Level 1");
        JButton level2Button = createButton("Level 2");
        JButton level3Button = createButton("Level 3");

        // Buttons hinzufügen
        buttonPanel.add(level1Button);
        buttonPanel.add(level2Button);
        buttonPanel.add(level3Button);

        add(buttonPanel, BorderLayout.CENTER);

        level1Button.addActionListener(e -> Main.startGame(new Level1("Level 1")));
        level2Button.addActionListener(e -> Main.startGame(new Level2("Level 2")));
        level3Button.addActionListener(e -> Main.startGame(new Level3("Level 3")));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24)); // Größere Schrift
        button.setBackground(new Color(70, 130, 180)); // Hintergrundfarbe setzen
        button.setForeground(Color.WHITE); // Schriftfarbe setzen
        button.setFocusPainted(false); // Kein Fokusrahmen
        button.setPreferredSize(new Dimension(200, 60)); // Doppelt so große Buttons
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 4), // Dicke Ränder
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Größerer Innenabstand
        ));
        return button;
    }
}
