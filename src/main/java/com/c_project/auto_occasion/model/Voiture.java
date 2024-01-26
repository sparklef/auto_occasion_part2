package com.c_project.auto_occasion.model;

public class Voiture {
    int idCar;
    String matricule;
    String nom_voiture;
    Marque marque_voiture;
    Categorie categorie;
    Detail_voiture detail;
//vv

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

    public Marque getMarque_voiture() {
        return marque_voiture;
    }

    public void setMarque_voiture(Marque marque_voiture) {
        this.marque_voiture = marque_voiture;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Detail_voiture getDetail() {
        return detail;
    }

    public void setDetail(Detail_voiture detail) {
        this.detail = detail;
    }
    

    public Voiture(int idCar, String matricule, String nom_voiture, Marque marque_voiture, Categorie categorie,
            Detail_voiture detail) {
        this.idCar = idCar;
        this.matricule = matricule;
        this.nom_voiture = nom_voiture;
        this.marque_voiture = marque_voiture;
        this.categorie = categorie;
        this.detail = detail;
    }

    public String getNom_voiture() {
        return nom_voiture;
    }

    public void setNom_voiture(String nom_voiture) {
        this.nom_voiture = nom_voiture;
    }


  
}
