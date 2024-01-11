package com.c_project.auto_occasion.model;

public class Detail_voiture {
    int idDetail;
    String couleur;
    int nbr_portes;
    String boite_devitesse;
    String source_energie;
    String type_voiture;
    int annee;
    String modele;

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

    public String getType_voiture() {
        return type_voiture;
    }

    public void setType_voiture(String type_voiture) {
        this.type_voiture = type_voiture;
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

    public Detail_voiture(int idDetail, String couleur, int nbr_portes, String boite_devitesse, String source_energie, String type_voiture, int annee, String modele) {
        this.idDetail = idDetail;
        this.couleur = couleur;
        this.nbr_portes = nbr_portes;
        this.boite_devitesse = boite_devitesse;
        this.source_energie = source_energie;
        this.type_voiture = type_voiture;
        this.annee = annee;
        this.modele = modele;
    }
}
