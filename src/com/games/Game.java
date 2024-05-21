package com.games;

import javax.swing.*;
import java.time.LocalDate;

public class Game {
    private String gameName;
    private String gameDescription;
    private String gameGenre;
    private String gameImage;
    private String gameCoverImage;
    private String exeLocation;
    private String folderLocation;


    public Game() {
    }

    public Game(String gameName, String gameDescription, String gameGenre, String gameImage, String gameCoverImage, String exeLocation, String folderLocation) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameGenre = gameGenre;
        this.gameImage = gameImage;
        this.gameCoverImage = gameCoverImage;
        this.exeLocation = exeLocation;
        this.folderLocation = folderLocation;
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

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGameImage() {
        return gameImage;
    }

    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    public String getGameCoverImage() {
        return gameCoverImage;
    }

    public void setGameCoverImage(String gameCoverImage) {
        this.gameCoverImage = gameCoverImage;
    }

    public String getExeLocation() {
        return exeLocation;
    }

    public void setExeLocation(String exeLocation) {
        this.exeLocation = exeLocation;
    }

    public String getFolderLocation() {
        return folderLocation;
    }

    public void setFolderLocation(String folderLocation) {
        this.folderLocation = folderLocation;
    }
}
