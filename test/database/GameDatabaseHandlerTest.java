package database;

import com.games.Game;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameDatabaseHandlerTest {

    @Test
    void updateGameDoesNotThrowException() {
        GameDatabaseHandler dbHandler = new GameDatabaseHandler();
        assertDoesNotThrow(() -> dbHandler.updateGame(new Game()));
    }

    @Test
    void getGamesReturnsEmptyListWhenUserHasNoGames() {
        GameDatabaseHandler dbHandler = new GameDatabaseHandler();
        List<Game> games = dbHandler.getGames("nonExistingUser");
        assertTrue(games.isEmpty());
    }

    @Test
    void deleteGameDoesNotThrowException() {
        GameDatabaseHandler dbHandler = new GameDatabaseHandler();
        assertDoesNotThrow(() -> dbHandler.deleteGame("existingGame", "testUser"));
    }
}