package com.c_project.auto_occasion.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Annonce {

    int idFavoris;
    int idAnnonce;
    int idUser;
    int idCar;
    int statut;
    Timestamp date_annonce;
    String lieu;
    String image_car;
    double prix;
    String description;
    boolean validation_annonce;
    
    public Annonce() {
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public Timestamp getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(Timestamp date_annonce) {
        this.date_annonce = date_annonce;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage_car() {
        return image_car;
    }

    public void setImage_car(String image_car) {
        this.image_car = image_car;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValidation_annonce() {
        return validation_annonce;
    }

    public void setValidation_annonce(boolean validation_annonce) {
        this.validation_annonce = validation_annonce;
    }

    public Annonce(int idAnnonce, int idUser, int idCar, int statut, Timestamp date_annonce, String lieu, String image_car, double prix, String description, boolean validation_annonce) {
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.idCar = idCar;
        this.statut = statut;
        this.date_annonce = date_annonce;
        this.lieu = lieu;
        this.image_car = image_car;
        this.prix = prix;
        this.description = description;
        this.validation_annonce = validation_annonce;
    }

    public Annonce(int idFavoris, int idAnnonce, int idUser, int idCar, int statut, Timestamp date_annonce, String lieu, String image_car, double prix, String description, boolean validation_annonce) {
        this.idFavoris = idFavoris;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.idCar = idCar;
        this.statut = statut;
        this.date_annonce = date_annonce;
        this.lieu = lieu;
        this.image_car = image_car;
        this.prix = prix;
        this.description = description;
        this.validation_annonce = validation_annonce;
    }
}
