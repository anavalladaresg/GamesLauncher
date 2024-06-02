package com.games;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void creatingGameWithParametersSetsAllFieldsCorrectly() {
        Game game = new Game("Game1", "Description1", "Genre1", "Image1", "CoverImage1", "ExeLocation1", "FolderLocation1");

        assertEquals("Game1", game.getGameName());
        assertEquals("Description1", game.getGameDescription());
        assertEquals("Genre1", game.getGameGenre());
        assertEquals("Image1", game.getGameImage());
        assertEquals("CoverImage1", game.getGameCoverImage());
        assertEquals("ExeLocation1", game.getExeLocation());
        assertEquals("FolderLocation1", game.getFolderLocation());
    }

    @Test
    void settingGameNameUpdatesName() {
        Game game = new Game();
        game.setGameName("NewGameName");

        assertEquals("NewGameName", game.getGameName());
    }

    @Test
    void settingGameDescriptionUpdatesDescription() {
        Game game = new Game();
        game.setGameDescription("NewGameDescription");

        assertEquals("NewGameDescription", game.getGameDescription());
    }

    @Test
    void settingGameGenreUpdatesGenre() {
        Game game = new Game();
        game.setGameGenre("NewGameGenre");

        assertEquals("NewGameGenre", game.getGameGenre());
    }

    @Test
    void settingGameImageUpdatesImage() {
        Game game = new Game();
        game.setGameImage("NewGameImage");

        assertEquals("NewGameImage", game.getGameImage());
    }

    @Test
    void settingGameCoverImageUpdatesCoverImage() {
        Game game = new Game();
        game.setGameCoverImage("NewGameCoverImage");

        assertEquals("NewGameCoverImage", game.getGameCoverImage());
    }

    @Test
    void settingExeLocationUpdatesExeLocation() {
        Game game = new Game();
        game.setExeLocation("NewExeLocation");

        assertEquals("NewExeLocation", game.getExeLocation());
    }

    @Test
    void settingFolderLocationUpdatesFolderLocation() {
        Game game = new Game();
        game.setFolderLocation("NewFolderLocation");

        assertEquals("NewFolderLocation", game.getFolderLocation());
    }
}