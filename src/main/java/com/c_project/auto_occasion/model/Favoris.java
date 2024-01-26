package com.c_project.auto_occasion.model;

public class Favoris {
    int idFavoris;
    int idAnnonce;

    int id_user;

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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Favoris(int idFavoris, int idAnnonce, int id_user) {
        this.idFavoris = idFavoris;
        this.idAnnonce = idAnnonce;
        this.id_user = id_user;
    }
}
