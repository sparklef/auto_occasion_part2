package com.c_project.auto_occasion.model;

public class Statistique {
    int totalutilisateur;
    int nombreannonceconfirmer;
    int totalvoiture;
    double chiffreaffaire;

 

    public Statistique() {
    }
    public Statistique(int totalutilisateur, int nombreannonceconfirmer, int totalvoiture, double chiffreaffaire) {
        this.totalutilisateur = totalutilisateur;
        this.nombreannonceconfirmer = nombreannonceconfirmer;
        this.totalvoiture = totalvoiture;
        this.chiffreaffaire = chiffreaffaire;
    }

    
   
    public int getTotalutilisateur() {
        return totalutilisateur;
    }
    public void setTotalutilisateur(int totalutilisateur) {
        this.totalutilisateur = totalutilisateur;
    }
    public int getNombreannonceconfirmer() {
        return nombreannonceconfirmer;
    }
    public void setNombreannonceconfirmer(int nombreannonceconfirmer) {
        this.nombreannonceconfirmer = nombreannonceconfirmer;
    }
    public int getTotalvoiture() {
        return totalvoiture;
    }
    public void setTotalvoiture(int totalvoiture) {
        this.totalvoiture = totalvoiture;
    }
    public double getChiffreaffaire() {
        return chiffreaffaire;
    }
    public void setChiffreaffaire(double chiffreaffaire) {
        this.chiffreaffaire = chiffreaffaire;
    }
  

    
}
