package com.games;

/**
 * La clase Game es un objeto que representa un juego.
 */
public class Game {
    /**
     * Nombre del juego.
     */
    private String gameName;

    /**
     * Descripción del juego.
     */
    private String gameDescription;

    /**
     * Género del juego.
     */
    private String gameGenre;

    /**
     * Imagen del juego.
     */
    private String gameImage;

    /**
     * Portada del juego.
     */
    private String gameCoverImage;

    /**
     * Ubicación del ejecutable del juego.
     */
    private String exeLocation;

    /**
     * Ubicación de la carpeta del juego.
     */
    private String folderLocation;

    /**
     * Constructor por defecto.
     */
    public Game() {
    }

    /**
     * Constructor con parámetros.
     * @param gameName Nombre del juego.
     * @param gameDescription Descripción del juego.
     * @param gameGenre Género del juego.
     * @param gameImage Imagen del juego.
     * @param gameCoverImage Portada del juego.
     * @param exeLocation Ubicación del ejecutable del juego.
     * @param folderLocation Ubicación de la carpeta del juego.
     */
    public Game(String gameName, String gameDescription, String gameGenre, String gameImage, String gameCoverImage, String exeLocation, String folderLocation) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameGenre = gameGenre;
        this.gameImage = gameImage;
        this.gameCoverImage = gameCoverImage;
        this.exeLocation = exeLocation;
        this.folderLocation = folderLocation;
    }

    /**
     * Obtiene el nombre del juego.
     * @return Nombre del juego.
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Establece el nombre del juego.
     * @param gameName Nombre del juego.
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Obtiene la descripción del juego.
     * @return Descripción del juego.
     */
    public String getGameDescription() {
        return gameDescription;
    }

    /**
     * Establece la descripción del juego.
     * @param gameDescription Descripción del juego.
     */
    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    /**
     * Obtiene el género del juego.
     * @return Género del juego.
     */
    public String getGameGenre() {
        return gameGenre;
    }

    /**
     * Establece el género del juego.
     * @param gameGenre Género del juego.
     */
    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    /**
     * Obtiene la imagen del juego.
     * @return Imagen del juego.
     */
    public String getGameImage() {
        return gameImage;
    }

    /**
     * Establece la imagen del juego.
     * @param gameImage Imagen del juego.
     */
    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    /**
     * Obtiene la portada del juego.
     * @return Portada del juego.
     */
    public String getGameCoverImage() {
        return gameCoverImage;
    }

    /**
     * Establece la portada del juego.
     * @param gameCoverImage Portada del juego.
     */
    public void setGameCoverImage(String gameCoverImage) {
        this.gameCoverImage = gameCoverImage;
    }

    /**
     * Obtiene la ubicación del ejecutable del juego.
     * @return Ubicación del ejecutable del juego.
     */
    public String getExeLocation() {
        return exeLocation;
    }

    /**
     * Establece la ubicación del ejecutable del juego.
     * @param exeLocation Ubicación del ejecutable del juego.
     */
    public void setExeLocation(String exeLocation) {
        this.exeLocation = exeLocation;
    }

    /**
     * Obtiene la ubicación de la carpeta del juego.
     * @return Ubicación de la carpeta del juego.
     */
    public String getFolderLocation() {
        return folderLocation;
    }

    /**
     * Establece la ubicación de la carpeta del juego.
     * @param folderLocation Ubicación de la carpeta del juego.
     */
    public void setFolderLocation(String folderLocation) {
        this.folderLocation = folderLocation;
    }
}
