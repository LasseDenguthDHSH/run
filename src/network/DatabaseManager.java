package src.network;

import src.level.Level;
import java.sql.*;

public class DatabaseManager {
    static String url = "jdbc:mysql://mysql-3de56c2b-jump.c.aivencloud.com:17474/jump?ssl-mode=REQUIRED";
    static String user = "avnadmin";
    static String password = "AVNS_l0bMjzlvjMRQ2-Oqzb7";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveSpielzeit(String name, long zeit, String zeitAnzeige, Level level) {
        String checkSql = "SELECT bestzeit FROM bestenliste WHERE name = ? AND level = ?";
        String updateSql = "UPDATE bestenliste SET bestzeit = ?, zeitanzeige = ? WHERE name = ? AND level = ?";
        String insertSql = "INSERT INTO bestenliste (name, bestzeit, zeitanzeige, level) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, name);
            checkStmt.setInt(2, level.getId());

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                long bestzeit = rs.getLong("bestzeit");

                if (zeit < bestzeit) {
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setLong(1, zeit);
                        updateStmt.setString(2, zeitAnzeige);
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
                    insertStmt.setLong(2, zeit);
                    insertStmt.setString(3, zeitAnzeige);
                    insertStmt.setInt(4, level.getId());
                    insertStmt.executeUpdate();
                    System.out.println("Neue Bestzeit gespeichert für " + name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getSpielzeiten() {
        String sql = "SELECT * FROM bestenliste ORDER BY bestzeit ASC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + ": " + rs.getString("zeitanzeige") + ", "
                        + rs.getString("level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}