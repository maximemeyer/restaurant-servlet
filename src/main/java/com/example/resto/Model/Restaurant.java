package com.example.resto.Model;

/**
 * Modele d'un objet Restaurant
 */
public class Restaurant {
    int idResto;
    String nomResto;
    String adresseResto;
    String typeCuisine;

    public Restaurant(int idResto, String nomResto, String adresseResto, String typeCuisine) {
        this.idResto = idResto;
        this.nomResto = nomResto;
        this.adresseResto = adresseResto;
        this.typeCuisine = typeCuisine;
    }

    public int getIdResto() {
        return idResto;
    }

    public void setIdResto(int idResto) {
        this.idResto = idResto;
    }

    public String getNomResto() {
        return nomResto;
    }

    public void setNomResto(String nomResto) {
        this.nomResto = nomResto;
    }

    public String getAdresseResto() {
        return adresseResto;
    }

    public void setAdresseResto(String adresseResto) {
        this.adresseResto = adresseResto;
    }

    public String getTypeCuisine() {
        return typeCuisine;
    }

    public void setTypeCuisine(String typeCuisine) {
        this.typeCuisine = typeCuisine;
    }
}
