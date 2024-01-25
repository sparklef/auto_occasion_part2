package com.c_project.auto_occasion.model;

import java.sql.Date;

public class Detail_voiture {
    int idDetail;
    String couleur;
    int nbr_portes;
    String boite_devitesse;
    String source_energie;
    int annee;
    String modele;
    //attribut en plus pour faire la recherche avance
    Date date_annonce;
    String lieu;
    String image_car;
    String description_annonce;
    String categorie;
    double prix;
    String matricule;
    String marque;

    public Detail_voiture() {
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNbr_portes() {
        return nbr_portes;
    }

    public void setNbr_portes(int nbr_portes) {
        this.nbr_portes = nbr_portes;
    }

    public String getBoite_devitesse() {
        return boite_devitesse;
    }

    public void setBoite_devitesse(String boite_devitesse) {
        this.boite_devitesse = boite_devitesse;
    }

    public String getSource_energie() {
        return source_energie;
    }

    public void setSource_energie(String source_energie) {
        this.source_energie = source_energie;
    }

   

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }


    

    public Date getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(Date date_annonce) {
        this.date_annonce = date_annonce;
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

    public String getDescription_annonce() {
        return description_annonce;
    }

    public void setDescription_annonce(String description_annonce) {
        this.description_annonce = description_annonce;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Detail_voiture(int idDetail, String couleur, int nbr_portes, String boite_devitesse, String source_energie, int annee, String modele) {
        this.idDetail = idDetail;
        this.couleur = couleur;
        this.nbr_portes = nbr_portes;
        this.boite_devitesse = boite_devitesse;
        this.source_energie = source_energie;
        this.annee = annee;
        this.modele = modele;
    }

    
    public Detail_voiture(String couleur, int nbr_portes, String boite_devitesse, String source_energie, int annee,
              String modele, Date date_annonce, String lieu, String image_car, String description_annonce,
              String categorie, double prix, String matricule, String marque) {
          this.couleur = couleur;
          this.nbr_portes = nbr_portes;
          this.boite_devitesse = boite_devitesse;
          this.source_energie = source_energie;
          this.annee = annee;
          this.modele = modele;
          this.date_annonce = date_annonce;
          this.lieu = lieu;
          this.image_car = image_car;
          this.description_annonce = description_annonce;
          this.categorie = categorie;
          this.prix = prix;
          this.matricule = matricule;
          this.marque = marque;
          }

}
