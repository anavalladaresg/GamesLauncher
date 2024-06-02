package database;

import com.games.Game;
import com.launcher.functional.mainpanel.LibraryController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la conexión con la base de datos de los juegos.
 */
public class GameDatabaseHandler extends DatabaseHandler {

    /**
     * Crea un nuevo GameDatabaseHandler.
     */
    public GameDatabaseHandler() {
        super();
    }

    /**
     * Actualiza un juego en la base de datos.
     *
     * @param game Juego a actualizar.
     */
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

    /**
     * Obtiene los juegos de un usuario.
     *
     * @param userName Nombre de usuario.
     * @return Lista de juegos.
     */
    public List<Game> getGames(String userName) {
        List<Game> games = new ArrayList<>();
        String SQL = "SELECT games.* FROM games JOIN user_games ON games.id = user_games.game_id JOIN users ON user_games.user_id = users.id WHERE users.userName = ?";

        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

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

    /**
     * Añade un juego a la base de datos.
     * @param game Juego a añadir.
     * @param userName Nombre de usuario.
     */
    public void addGame(Game game, String userName) {
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

            String query2 = "INSERT INTO user_games (user_id, game_id) SELECT users.id, games.id FROM users, games WHERE users.userName = ? AND games.name = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, userName);
            pstmt2.setString(2, game.getGameName());
            pstmt2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un juego de la base de datos.
     *
     * @param gameName Nombre del juego.
     * @param userName Nombre de usuario.
     */
    public void deleteGame(String gameName, String userName) {
        String SQL = "DELETE FROM user_games WHERE game_id = (SELECT id FROM games WHERE name = ?) AND user_id = (SELECT id FROM users WHERE userName = ?)";
        connect();
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, gameName);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}