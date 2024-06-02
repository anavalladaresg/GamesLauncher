package database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseHandlerTest {

    @Test
    void databaseHandlerCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
        });
    }

    @Test
    void connectReturnsConnection() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Connection conn = dbHandler.connect();
        assertNotNull(conn);
    }
}