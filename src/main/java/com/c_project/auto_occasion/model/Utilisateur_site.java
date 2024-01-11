package com.c_project.auto_occasion.model;

public class Utilisateur_site {
    int idUser;
    String email;
    String nom;
    String prenom;
    String mdp;
    int contact;

    public Utilisateur_site() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public Utilisateur_site(int idUser, String email, String nom, String prenom, String mdp, int contact) {
        this.idUser = idUser;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.contact = contact;
    }
}
