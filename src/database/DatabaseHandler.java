package database;

import com.games.Game;

import java.sql.*;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/xynx";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "debian";
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
    String regex = "^[a-zA-Z0-9]+$"; // Solo permite caracteres alfanuméricos

    if (!userName.matches(regex) || !password.matches(regex)) {
        System.out.println("Username and password can only contain alphanumeric characters.");
        return;
    }

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

    /**
     * Add a game to the database
     * @param game the game to add
     */
    public void addGame(Game game) {
        connect();
        try {
            String query = "INSERT INTO games (name, description, genre, image, coverimage, exe, folder) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, game.getGameName());
            pstmt.setString(2, game.getGameDescription());
            pstmt.setString(3, game.getGameGenre());
            pstmt.setString(4, game.getGameImage());
            pstmt.setString(5, game.getGameCoverImage());
            pstmt.setString(6, game.getExeLocation());
            pstmt.setString(7, game.getFolderLocation());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}