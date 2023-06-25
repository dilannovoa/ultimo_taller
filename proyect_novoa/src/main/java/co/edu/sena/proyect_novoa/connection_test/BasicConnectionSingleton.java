package co.edu.sena.proyect_novoa.connection_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicConnectionSingleton {
    private static String url = "jdbc:mysql://localhost:3306/BaseDatos?serverTimezone=America/Bogota";
    private static String user = "root";
    private static String pass = "";
    private static Connection conn;
    public static Connection getInstance()
            throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, user, pass);
        }
        return conn;
    }
} // BasicConnectionSingleton
