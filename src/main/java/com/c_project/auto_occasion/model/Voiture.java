package com.c_project.auto_occasion.model;

public class Voiture {
    int idCar;
    String matricule;
    double prix;
    //int idMarque;
    int idmarque;
    int idcategorie;
    int id_detail;

    public Voiture() {
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdmarque() {
        return idmarque;
    }

    public void setIdmarque(int idmarque) {
        this.idmarque = idmarque;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public Voiture(int idCar, String matricule, double prix, int idmarque, int idcategorie, int id_detail) {
        this.idCar = idCar;
        this.matricule = matricule;
        this.prix = prix;
        this.idmarque = idmarque;
        this.idcategorie = idcategorie;
        this.id_detail = id_detail;
    }
}
