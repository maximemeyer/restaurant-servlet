package com.example.resto.Model;

/**
 * Modele d'une objet Recette
 */
public class Recette {

    int idRecette;
    int idResto;
    String nomRecette;
    int idTypeRecette;
    double prix;

    public Recette(int idRecette, int idResto, String nomRecette, int idTypeRecette, double prix) {
        this.idRecette = idRecette;
        this.idResto = idResto;
        this.nomRecette = nomRecette;
        this.idTypeRecette = idTypeRecette;
        this.prix = prix;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public int getIdResto() {
        return idResto;
    }

    public void setIdResto(int idResto) {
        this.idResto = idResto;
    }

    public String getNomRecette() {
        return nomRecette;
    }

    public void setNomRecette(String nomRecette) {
        this.nomRecette = nomRecette;
    }

    public int getIdTypeRecette() {
        return idTypeRecette;
    }

    public void setIdTypeRecette(int idTypeRecette) {
        this.idTypeRecette = idTypeRecette;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
