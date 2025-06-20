package src.network;

import src.level.Level;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseManager {
    private static String url = "jdbc:mysql://mysql-3de56c2b-jump.c.aivencloud.com:17474/jump?ssl-mode=REQUIRED";
    private static String user = "avnadmin";
    private static String password = "AVNS_l0bMjzlvjMRQ2-Oqzb7";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveSpielzeit(String name, long time, String timeDisplay, Level level) {
        String checkSql = "SELECT bestzeit FROM bestenliste WHERE name = ? AND level = ?";
        String updateSql = "UPDATE bestenliste SET bestzeit = ?, zeitanzeige = ? WHERE name = ? AND level = ?";
        String insertSql = "INSERT INTO bestenliste (name, bestzeit, zeitanzeige, level) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, name);
            checkStmt.setInt(2, level.getId());

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                long recordTime = rs.getLong("bestzeit");

                if (time < recordTime) {
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setLong(1, time);
                        updateStmt.setString(2, timeDisplay);
                        updateStmt.setString(3, name);
                        updateStmt.setInt(4, level.getId());
                        updateStmt.executeUpdate();
                        System.out.println("Bestzeit aktualisiert für " + name);
                    }
                } else {
                    System.out.println("Bestehende Zeit ist besser. Keine Aktualisierung.");
                }
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, name);
                    insertStmt.setLong(2, time);
                    insertStmt.setString(3, timeDisplay);
                    insertStmt.setInt(4, level.getId());
                    insertStmt.executeUpdate();
                    System.out.println("Neue Bestzeit gespeichert für " + name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getBestenliste(Level level) {
        Map<String, String> top10List = new LinkedHashMap<>();
        String sql = "SELECT name, zeitanzeige FROM bestenliste WHERE level = ? ORDER BY bestzeit ASC";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, level.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                top10List.put(rs.getString("name"), rs.getString("zeitanzeige"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return top10List;
    }
}