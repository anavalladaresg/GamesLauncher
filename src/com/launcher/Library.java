package com.launcher;

import com.games.Game;

import java.util.ArrayList;

public class Library {
    ArrayList<Game> games = new ArrayList<Game>();

    public void addGame(Game game) {
        games.add(game);
    }
    public void removeGame(Game game) {
        games.remove(game);
    }
    public ArrayList<Game> searchGame(String gameName) {
        return null;
    }

}
