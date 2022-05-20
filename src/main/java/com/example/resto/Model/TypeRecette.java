package com.example.resto.Model;

/**
 * Modele d'un objet TypeRecette
 */
public class TypeRecette {
    int idType;
    String libelle;

    public TypeRecette(int idType, String libelle) {
        this.idType = idType;
        this.libelle = libelle;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
