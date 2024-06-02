package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDatabaseHandlerTest {

    @Test
    void addUserDoesNotThrowException() {
        UserDatabaseHandler dbHandler = new UserDatabaseHandler();
        assertDoesNotThrow(() -> dbHandler.addUser("testUser", "testPassword"));
    }

    @Test
    void userExistsReturnsTrueWhenUserExists() {
        UserDatabaseHandler dbHandler = new UserDatabaseHandler();
        dbHandler.addUser("existingUser", "testPassword");
        assertTrue(dbHandler.userExists("existingUser"));
    }

    @Test
    void userExistsReturnsFalseWhenUserDoesNotExist() {
        UserDatabaseHandler dbHandler = new UserDatabaseHandler();
        assertFalse(dbHandler.userExists("nonExistingUser"));
    }

    @Test
    void getPasswordReturnsCorrectPassword() {
        UserDatabaseHandler dbHandler = new UserDatabaseHandler();
        dbHandler.addUser("testUser", "testPassword");
        assertEquals("testPassword", dbHandler.getPassword("testUser"));
    }

    @Test
    void getPasswordReturnsNullWhenUserDoesNotExist() {
        UserDatabaseHandler dbHandler = new UserDatabaseHandler();
        assertNull(dbHandler.getPassword("nonExistingUser"));
    }
}