package com.example.resto.DAO;

import com.example.resto.Model.Restaurant;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe DAO qui controle la table restaurant
 */
public class RestaurantDAO {
    Connection cnx;

    /**
     * constructeur
     * @throws ClassNotFoundException
     */
    public RestaurantDAO() throws ClassNotFoundException {
        cnx = DbConnection.connectionDB();
    }

    /**
     * Ajoute un restaurant à la BDD
     * @param nom
     * @param adresse
     * @param type
     * @throws SQLException
     */
    public void ajouteRestaurant(String nom, String adresse, String type) throws SQLException {
        PreparedStatement stm = cnx.prepareStatement("INSERT INTO restaurant (nom, adresse, type_cuisine) VALUES (?, ?, ?);");
        stm.setString(1, nom);
        stm.setString(2, adresse);
        stm.setString(3, type);
        stm.execute();
    }

    /**
     * Retourne tout les restaurants de la BDD
     * @return
     * @throws SQLException
     */
    public ArrayList<Restaurant> getAllRestaurant() throws SQLException {
        ArrayList<Restaurant> lesRestaurants = new ArrayList<Restaurant>();
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM restaurant");
        while (res.next()) {
            int id = res.getInt("id_resto");
            String nom = res.getString("nom");
            String adresse = res.getString("adresse");
            String typeCuisine = res.getString("type_cuisine");
            Restaurant resto = new Restaurant(id, nom, adresse, typeCuisine);
            lesRestaurants.add(resto);
        }
        return lesRestaurants;
    }

    /**
     * @param id
     * @return un restaurant grâce à son ID
     * @throws SQLException
     */
    public Restaurant getRestaurantById(int id) throws SQLException {
        Restaurant resto = null;
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM restaurant where id_resto = " + id);
        while (res.next()) {
            int idResto = res.getInt("id_resto");
            String nom = res.getString("nom");
            String adresse = res.getString("adresse");
            String typeCuisine = res.getString("type_cuisine");
            resto = new Restaurant(idResto, nom, adresse, typeCuisine);
        }        
        return resto;
    }

    /**
     * @param type
     * @return un restaurant en fonction de son type de cuisine
     * @throws SQLException
     */
    public ArrayList<Restaurant> getRestaurantByType(String type) throws SQLException {
        ArrayList<Restaurant> listeRestaurants = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM restaurant where type_cuisine = '" + type + "'");
        while (res.next()) {
            int idResto = res.getInt("id_resto");
            String nom = res.getString("nom");
            String adresse = res.getString("adresse");
            String typeCuisine = res.getString("type_cuisine");
            listeRestaurants.add(new Restaurant(idResto, nom, adresse, typeCuisine));
        }
        return listeRestaurants;
    }

    public ArrayList<Restaurant> getRestaurantByNom(String nomRecherche) throws SQLException {
        ArrayList<Restaurant> listeRestaurants = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM restaurant where nom = '" + nomRecherche + "'");
        while (res.next()) {
            int idResto = res.getInt("id_resto");
            String nomResto = res.getString("nom");
            String adresse = res.getString("adresse");
            String typeCuisine = res.getString("type_cuisine");
            listeRestaurants.add(new Restaurant(idResto, nomResto, adresse, typeCuisine));
        }
        return listeRestaurants;
    }

    /**
     * Modifie un restaurant
     * @param restaurant
     * @throws SQLException
     */
    public void modifRestaurant(Restaurant restaurant) throws SQLException {
        PreparedStatement stm = cnx.prepareStatement("UPDATE restaurant SET nom = ?, adresse = ?, type_cuisine = ? WHERE id_resto = ?");
        stm.setString(1, restaurant.getNomResto());
        stm.setString(2, restaurant.getAdresseResto());
        stm.setString(3, restaurant.getTypeCuisine());
        stm.setInt(4, restaurant.getIdResto());
        stm.execute();
    }

    /**
     * Supprime un restaurant
     * @param id
     * @throws SQLException
     */
    public void supprimerRestaurant(int id) throws SQLException {
        PreparedStatement stm = cnx.prepareStatement("DELETE FROM restaurant WHERE id_resto = ?");
        stm.setInt(1, id);
        stm.execute();
    }
}
