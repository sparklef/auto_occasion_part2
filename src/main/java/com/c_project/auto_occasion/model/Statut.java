package com.c_project.auto_occasion.model;

public class Statut {
    int idstatut;
    String statut;

    public Statut() {
    }

    public int getIdstatut() {
        return idstatut;
    }

    public void setIdstatut(int idstatut) {
        this.idstatut = idstatut;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Statut(int idstatut, String statut) {
        this.idstatut = idstatut;
        this.statut = statut;
    }
}
