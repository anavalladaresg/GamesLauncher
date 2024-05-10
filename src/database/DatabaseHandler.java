package database;

import java.sql.*;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/xynx";
    private static final String DATABASE_USER = "anavalladares";
    private static final String DATABASE_PASSWORD = "aaaa";
    private Connection conn = null;

    /**
     * Connect to the database
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a new user
     * @param userName the username
     * @param password the password
     */
    public void addUser(String userName, String password) {
        String SQL = "INSERT INTO users(userName, password) VALUES(?,?)";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check if a user exists
     * @param userName the username
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(String userName) {
        String SQL = "SELECT * FROM users WHERE userName = ?";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, userName);
            return pstmt.executeQuery().next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Get the password of a user
     * @param userName the username
     * @return the password of the user
     */
    public String getPassword(String userName) {
        String SQL = "SELECT password FROM users WHERE userName = ?";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}