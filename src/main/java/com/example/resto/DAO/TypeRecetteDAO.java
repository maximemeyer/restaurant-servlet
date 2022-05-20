package com.example.resto.DAO;

import com.example.resto.Model.Restaurant;
import com.example.resto.Model.TypeRecette;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe DAO qui controle le type d'une recette
 */
public class TypeRecetteDAO {
    Connection cnx;

    /**
     * Constructeur
     * @throws ClassNotFoundException
     */
    public TypeRecetteDAO() throws ClassNotFoundException {
        this.cnx = DbConnection.connectionDB();
    }

    /**
     * @return tout les types de recette
     * @throws SQLException
     */
    public ArrayList<TypeRecette> getAllType() throws SQLException {
        ArrayList<TypeRecette> lesTypes = new ArrayList<TypeRecette>();
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM type_recetterestaurant");
        while (res.next()) {
            int idType = res.getInt("id_type");
            String libelle = res.getString("libelle");
            TypeRecette typeRecette = new TypeRecette(idType, libelle);
            lesTypes.add(typeRecette);
        }
        return lesTypes;
    }

    /**
     * @param id
     * @return le libelle d'un type grâce à son ID
     * @throws SQLException
     */
    public String getTypeById(int id) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM type_recetterestaurant WHERE id_type = " + id);
        String libelle = null;
        while (res.next()) {
            libelle = res.getString("libelle");
        }
        return libelle;
    }
}
