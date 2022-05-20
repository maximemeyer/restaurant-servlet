package com.example.resto.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connection à la BDD MySQL
 */
public class DbConnection {
    public static Connection connectionDB() throws ClassNotFoundException {
        String url = "jdbc:mysql://127.0.0.1/restaurant";
        String login = "root";
        String pwd = "";

        Connection maConnexion = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            maConnexion = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connecté");
        } catch (SQLException sqle) {
            System.out.println("Erreur connexion " + sqle.getMessage());
        }
        return maConnexion;
    }
}
