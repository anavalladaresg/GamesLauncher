package com.launcher;

import com.games.Game;

import java.util.ArrayList;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String email;

    /**
     * List of games owned by the user
     */
    ArrayList<Game> gamesOwned = new ArrayList<Game>();

    public boolean login(String userName, String password) {
        return false;
    }
}

