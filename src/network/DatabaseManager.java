package src.network;

import src.level.Level;

import java.sql.*;
import java.util.Scanner;

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
    public static void saveSpielzeit(String spielerName, String zeit) {
        String sql = "INSERT INTO bestenliste (name, bestzeit, level) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, spielerName);
            stmt.setString(2, zeit);
            stmt.setString(3, "0");
            stmt.executeUpdate();
            System.out.println("Spielzeit gespeichert für " + spielerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getSpielzeiten() {
        String sql = "SELECT * FROM bestenliste ORDER BY bestzeit ASC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + ": " + rs.getString("bestzeit"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //moin


}