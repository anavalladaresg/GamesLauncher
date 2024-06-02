package database;

import java.sql.*;

/**
 * Clase que maneja la conexión con la base de datos.

 */
public class DatabaseHandler {

    /**
     * URL de la base de datos.
     */
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/xynx";

    /**
     * Usuario de la base de datos.
     */
    private static final String DATABASE_USER = "postgres";

    /**
     * Contraseña de la base de datos.
     */
    private static final String DATABASE_PASSWORD = "debian";

    /**
     * Conexión con la base de datos.
     */
    protected Connection conn = null;

    /**
     * Crea un nuevo DatabaseHandler.
     * Se encarga de cargar el driver de PostgreSQL y de conectarse a la base de datos.
     */
    public DatabaseHandler() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establece la conexión con la base de datos.
     *
     * @return
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}