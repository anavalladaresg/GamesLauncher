package com.launcher;

import com.games.Game;

import java.util.ArrayList;

public class User {
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
    private String email;

    /**
     * Sign up a new user
     *
     * @param userName username of the user
     * @param password password of the user
     * @param email    email of the user
     * @return true if the user is signed up successfully, false otherwise
     */
    public boolean signUp(String userName, String password, String email) {
        for (User u : users) {
            if (u.userName.equals(userName)) {
                return false;
            }
        }
        User newUser = new User();
        newUser.userName = userName;
        newUser.password = password;
        newUser.email = email;
        users.add(newUser);
        return true;
    }

    /**
     * Login a user
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