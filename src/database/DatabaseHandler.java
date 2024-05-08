package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/xynx";
    private static final String DATABASE_USER = "anavalladares";
    private static final String DATABASE_PASSWORD = "aaaa";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addUser(String userName, String password) {
        String SQL = "INSERT INTO users(userName, password) VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}