package database;

import com.games.Game;
import com.launcher.LibraryController;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/xynx";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "debian";
    private Connection conn = null;

    public DatabaseHandler() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

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
     *
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
     *
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
     *
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
     *
     * @param game the game to add
     */
    public void addGame(Game game) {
        connect();
        try {
            String gameImage = LibraryController.getImagePath(game.getGameImage());
            String gameCoverImage = LibraryController.getImagePath(game.getGameCoverImage());

            String query = "INSERT INTO games (name, description, genre, image, coverimage, exe, folder) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, game.getGameName());
            pstmt.setString(2, game.getGameDescription());
            pstmt.setString(3, game.getGameGenre());
            pstmt.setString(4, gameImage);
            pstmt.setString(5, gameCoverImage);
            pstmt.setString(6, game.getExeLocation());
            pstmt.setString(7, game.getFolderLocation());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all games from the database
     *
     * @return a list of games
     */
    public List<Game> getGames() {
        List<Game> games = new ArrayList<>();
        String SQL = "SELECT * FROM games";

        connect();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()) {
                Game game = new Game();
                game.setGameName(rs.getString("name"));
                game.setGameDescription(rs.getString("description"));
                game.setGameGenre(rs.getString("genre"));
                game.setGameImage(rs.getString("image"));
                game.setGameCoverImage(rs.getString("coverimage"));
                game.setExeLocation(rs.getString("exe"));
                game.setFolderLocation(rs.getString("folder"));
                games.add(game);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return games;
    }

    // Delete a game from the database
    public void deleteGame(String gameName) {
        String SQL = "DELETE FROM games WHERE name = ?";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, gameName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}