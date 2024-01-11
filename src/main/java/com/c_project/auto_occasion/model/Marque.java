package com.c_project.auto_occasion.model;

public class Marque {
    int idMarque;
    String marque;

    public Marque() {
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Marque(int idMarque, String marque) {
        this.idMarque = idMarque;
        this.marque = marque;
    }
}
