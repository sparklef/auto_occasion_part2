package com.c_project.auto_occasion.model;

public class Commission {
    Annonce idAnnonce;
    double commission_pourcent ;

    public Commission() {
    }

    public Commission(Annonce idAnnonce, double commission_pourcent) {
        this.idAnnonce = idAnnonce;
        this.commission_pourcent = commission_pourcent;
    }

    public Annonce getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Annonce idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public double getCommission_pourcent() {
        return commission_pourcent;
    }

    public void setCommission_pourcent(double commission_pourcent) {
        this.commission_pourcent = commission_pourcent;
    }

 
   
   


}
