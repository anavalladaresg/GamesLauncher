package com.launcher;

import com.games.Game;

import java.util.ArrayList;

public class Library {
    ArrayList<Game> games = new ArrayList<Game>();

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
}
