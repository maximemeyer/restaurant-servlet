package com.example.resto.DAO;

import com.example.resto.Model.Recette;
import com.example.resto.Model.Restaurant;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe DAO pour la gestion d'une recette
 */
public class RecetteDAO {
    Connection cnx;

    /**
     * Constructeur
     * @throws ClassNotFoundException
     */
    public RecetteDAO() throws ClassNotFoundException {
        this.cnx = DbConnection.connectionDB();
    }

    /**
     * @param idResto id du resto dont on souhaite les recettes
     * @return toutes les recettes pour un restaurant passé en paramètre
     * @throws SQLException
     */
    public ArrayList<Recette> getRecettesResto(int idResto) throws SQLException {
        ArrayList<Recette> listeRecette = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM recette where id_resto = " + idResto);
        while (res.next()) {
            int idRecette = res.getInt("id_recette");
            int idRestaurant = res.getInt("id_resto");
            String nomRecette = res.getString("nom_recette");
            int idTypeRecette = res.getInt("id_type_recette");
            double prix = res.getDouble("prix");
            Recette recette = new Recette(idRecette, idRestaurant, nomRecette, idTypeRecette, prix);
            listeRecette.add(recette);
        }
        return listeRecette;
    }

    /**
     * @param idRecette id de la recette que l'on souhaite
     * @return une recette
     * @throws SQLException
     */
    public Recette getRecetteById(int idRecette) throws SQLException {
        Recette recette = null;
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM recette where id_recette = " + idRecette);
        while (res.next()) {
            int id = res.getInt("id_recette");
            int idRestaurant = res.getInt("id_resto");
            String nomRecette = res.getString("nom_recette");
            int idTypeRecette = res.getInt("id_type_recette");
            double prix = res.getDouble("prix");
            recette = new Recette(id, idRestaurant, nomRecette, idTypeRecette, prix);
        }
        return recette;
    }

    /**
     * Ajoute une recette à la BDD
     * @param idResto
     * @param nomRecette
     * @param idType
     * @param prix
     * @throws SQLException
     */
    public void addRecetteToResto(int idResto, String nomRecette, int idType, double prix) throws SQLException {
        PreparedStatement stm = cnx.prepareStatement("INSERT INTO recette (id_resto, nom_recette, id_type_recette, prix) VALUES (?, ?, ?, ?);");
        stm.setInt(1, idResto);
        stm.setString(2, nomRecette);
        stm.setInt(3, idType);
        stm.setDouble(4, prix);
        stm.execute();
    }

    /**
     * Modifier une recette de la BDD
     * @param recette
     * @throws SQLException
     */
    public void modifRecetteToResto(Recette recette) throws SQLException {
        PreparedStatement stm = cnx.prepareStatement("UPDATE recette SET nom_recette = ?, id_type_recette = ?, prix = ? WHERE id_recette = ?");
        stm.setString(1, recette.getNomRecette());
        stm.setInt(2, recette.getIdTypeRecette());
        stm.setDouble(3, recette.getPrix());
        stm.setInt(4, recette.getIdRecette());
        stm.execute();
    }

    /**
     * Supprie une recette de la BDD
     * @param id
     * @throws SQLException
     */
    public void deleteRecetteById(int id) throws SQLException {
        PreparedStatement stm = cnx.prepareStatement("DELETE FROM recette WHERE id_recette = ?");
        stm.setInt(1, id);
        stm.execute();
    }
}
