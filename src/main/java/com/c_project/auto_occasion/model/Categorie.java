package com.c_project.auto_occasion.model;

public class Categorie {
    int idCategorie;
    String categorie;

    public Categorie() {
    }

    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Categorie(int idCategorie, String categorie) {
        this.idCategorie = idCategorie;
        this.categorie = categorie;
    }
}
