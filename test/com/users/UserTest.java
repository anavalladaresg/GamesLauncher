package com.users;

import com.games.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void userCanBeInstantiated() {
        assertDoesNotThrow(() -> {
            User user = new User();
        });
    }

    @Test
    void userIdIsUniqueForMultipleUsers() {
        User user1 = new User();
        User user2 = new User();
        assertNotEquals(user1.getUserId(), user2.getUserId());
    }

    @Test
    void userNameCanBeSetAndRetrieved() {
        User user = new User();
        user.setUserName("test");
        assertEquals("test", user.getUserName());
    }

    @Test
    void passwordCanBeSetAndRetrieved() {
        User user = new User();
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    void gamesOwnedCanBeSetAndRetrieved() {
        User user = new User();
        ArrayList<Game> games = new ArrayList<>();
        user.setGamesOwned(games);
        assertEquals(games, user.getGamesOwned());
    }

    @Test
    void usersCanBeSetAndRetrieved() {
        User user = new User();
        ArrayList<User> users = new ArrayList<>();
        user.setUsers(users);
        assertEquals(users, user.getUsers());
    }

    @Test
    void loginReturnsFalseWhenUserDoesNotExist() {
        User user = new User();
        assertFalse(user.login("nonexistent", "password"));
    }

    @Test
    void loginReturnsFalseWhenPasswordDoesNotMatch() {
        User user = new User();
        user.setUserName("test");
        user.setPassword("password");
        assertFalse(user.login("test", "wrongpassword"));
    }
}