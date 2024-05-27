package database;

import com.games.Game;
import com.launcher.LibraryController;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/xynx";
    private static final String DATABASE_USER = "anavalladares";
    private static final String DATABASE_PASSWORD = "aaaa";
    private Connection conn = null;

    public DatabaseHandler() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(String userName, String password) {
        String SQL = "INSERT INTO users(userName, password) VALUES(?,?)";
        String regex = "^[a-zA-Z0-9]+$";

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

    public void updateGame(Game game) {
        String SQL = "UPDATE games SET description = ?, genre = ?, image = ?, coverimage = ?, exe = ?, folder = ? WHERE name = ?";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, game.getGameDescription());
            pstmt.setString(2, game.getGameGenre());
            pstmt.setString(3, game.getGameImage());
            pstmt.setString(4, game.getGameCoverImage());
            pstmt.setString(5, game.getExeLocation());
            pstmt.setString(6, game.getFolderLocation());
            pstmt.setString(7, game.getGameName());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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