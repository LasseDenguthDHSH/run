package src.network;

import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/jump_game";
    private static final String USER = "root";
    private static final String PASSWORD = "moin12345";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void saveSpielzeit(String spielerName, String zeit) {
        String sql = "INSERT INTO bestlisten (spielername, zeit) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, spielerName);
            stmt.setString(2, zeit);
            stmt.executeUpdate();
            System.out.println("Spielzeit gespeichert für " + spielerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getSpielzeiten() {
        String sql = "SELECT * FROM bestlisten ORDER BY zeit ASC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("spielername") + ": " + rs.getString("zeit"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}