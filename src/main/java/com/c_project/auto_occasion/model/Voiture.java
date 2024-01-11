package com.c_project.auto_occasion.model;

public class Voiture {
    int idCar;
    int matricule;
    double prix;
    int idMarque;
    int idCategorie;
    int idDetail;

    public Voiture() {
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public Voiture(int idCar, int matricule, double prix, int idMarque, int idCategorie, int idDetail) {
        this.idCar = idCar;
        this.matricule = matricule;
        this.prix = prix;
        this.idMarque = idMarque;
        this.idCategorie = idCategorie;
        this.idDetail = idDetail;
    }
}
