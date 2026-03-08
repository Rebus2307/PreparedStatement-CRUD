package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario?" //Nombre de la base de datos
            + "allowPublicKeyRetrieval=true&"
            + "useSSL=false&"
            + "serverTimezone=UTC";

    private static final String USER = "root"; //Usuario de tu base de datos
    private static final String PASS = "23072003"; // Contraseña de tu base de datos
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado: " + e.getMessage());
            throw new SQLException("Driver no encontrado", e);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            throw e;
        }
    }
}