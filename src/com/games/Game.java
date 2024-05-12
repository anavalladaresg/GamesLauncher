package com.games;

import javax.swing.*;
import java.time.LocalDate;

public class Game {
    private int gameId;
    private String gameName;
    private String gameDescription;
    private double gamePrice;
    private String gameGenre;
    private LocalDate releaseDate;
    private String gameDeveloper;
    private double gameRating;
    private String gameImage;

    public Game() {
    }

    public Game(int gameId, String gameName, String gameDescription, double gamePrice, String gameGenre, LocalDate releaseDate, String gameDeveloper, double gameRating, String gameImage) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gamePrice = gamePrice;
        this.gameGenre = gameGenre;
        this.releaseDate = releaseDate;
        this.gameDeveloper = gameDeveloper;
        this.gameRating = gameRating;
        this.gameImage = gameImage;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(double gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGameDeveloper() {
        return gameDeveloper;
    }

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }

    public double getGameRating() {
        return gameRating;
    }

    public void setGameRating(double gameRating) {
        this.gameRating = gameRating;
    }

    public String getGameImage() {
        return gameImage;
    }

    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }
}
