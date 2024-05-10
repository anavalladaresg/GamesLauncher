package com.launcher;

import com.games.Game;
import database.DatabaseHandler;

import java.util.ArrayList;

public class User {
    private static int userIdCounter = 0;
    /**
     * List of games owned by the user
     */
    ArrayList<Game> gamesOwned = new ArrayList<Game>();
    /**
     * List of users in the system
     */
    ArrayList<User> users = new ArrayList<User>();
    private int userId;
    private String userName;
    private String password;

    /**
     * Every time a new user is created, the userId is incremented by 1
     */
    public User() {
        userId = userIdCounter++;
    }

    public static int getUserIdCounter() {
        return userIdCounter;
    }

    public static void setUserIdCounter(int userIdCounter) {
        User.userIdCounter = userIdCounter;
    }

    public ArrayList<Game> getGamesOwned() {
        return gamesOwned;
    }

    public void setGamesOwned(ArrayList<Game> gamesOwned) {
        this.gamesOwned = gamesOwned;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Login a user
     * @param userName the username
     * @param password the password
     * @return true if the user is logged in successfully, false otherwise
     */
    public boolean login(String userName, String password) {
        DatabaseHandler db = new DatabaseHandler();
        db.connect();
        if (db.userExists(userName)) {
            String storedPassword = db.getPassword(userName);
            return storedPassword != null && storedPassword.equals(password);
        }
        return false;
    }
}