package com.launcher;

import com.games.Game;

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
     * Every time a new user is created, the userId is incremented by 1
     */
    public User() {
        userId = userIdCounter++;
    }

    /**
     * Sign up a new user
     *
     * @param userName username of the user
     * @param password password of the user
     * @return true if the user is signed up successfully, false otherwise
     */
    public boolean signUp(String userName, String password) {
        for (User u : users) {
            if (u.userName.equals(userName)) {
                return false;
            }
        }
        User newUser = new User();
        newUser.userName = userName;
        newUser.password = password;
        users.add(newUser);
        return true;
    }

    /**
     * Login a user
     *
     * @param userName username of the user
     * @param password password of the user
     * @return true if the user is logged in successfully, false otherwise
     */

    public boolean login(String userName, String password) {
        for (User u : users) {
            if (u.userName.equals(userName) && (u.password.equals(password))) {
                return true;
            }
        }
        return false;
    }

    public void addGameToLibrary(Game game) {
    }

    public void removeGameFromLibrary(Game game) {
    }
}