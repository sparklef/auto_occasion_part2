package com.c_project.auto_occasion.model;

public class Stat {
    String categorie;
    int nbcategorie;
    String marque;
    int nbmarque;
    String couleur;
    int nbcouleur;

    public Stat(String categorie, int nbcategorie, String marque, int nbmarque, String couleur, int nbcouleur) {
        this.categorie = categorie;
        this.nbcategorie = nbcategorie;
        this.marque = marque;
        this.nbmarque = nbmarque;
        this.couleur = couleur;
        this.nbcouleur = nbcouleur;
    }

    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public int getNbcategorie() {
        return nbcategorie;
    }
    public void setNbcategorie(int nbcategorie) {
        this.nbcategorie = nbcategorie;
    }
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public int getNbmarque() {
        return nbmarque;
    }
    public void setNbmarque(int nbmarque) {
        this.nbmarque = nbmarque;
    }
    public String getCouleur() {
        return couleur;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    public int getNbcouleur() {
        return nbcouleur;
    }
    public void setNbcouleur(int nbcouleur) {
        this.nbcouleur = nbcouleur;
    }
}
