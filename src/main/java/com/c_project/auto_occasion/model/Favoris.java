package com.c_project.auto_occasion.model;

public class Favoris {
    int idFavoris;
    int idAnnonce;

    public Favoris() {
    }

    public int getIdFavoris() {
        return idFavoris;
    }

    public void setIdFavoris(int idFavoris) {
        this.idFavoris = idFavoris;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Favoris(int idFavoris, int idAnnonce) {
        this.idFavoris = idFavoris;
        this.idAnnonce = idAnnonce;
    }
}
