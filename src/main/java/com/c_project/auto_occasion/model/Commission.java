package com.c_project.auto_occasion.model;

public class Commission {
    Annonce idAnnonce;
    double commission_percentage ;
    double   commission_amount ;
    String  commission_status;
    public Commission() {
    }
   
   
    public Commission(Annonce idAnnonce, double commission_percentage, double commission_amount,
            String commission_status) {
        this.idAnnonce = idAnnonce;
        this.commission_percentage = commission_percentage;
        this.commission_amount = commission_amount;
        this.commission_status = commission_status;
    }


    public double getCommission_percentage() {
        return commission_percentage;
    }
    public void setCommission_percentage(double commission_percentage) {
        this.commission_percentage = commission_percentage;
    }
    public double getCommission_amount() {
        return commission_amount;
    }
    public void setCommission_amount(double commission_amount) {
        this.commission_amount = commission_amount;
    }
    public String getCommission_status() {
        return commission_status;
    }
    public void setCommission_status(String commission_status) {
        this.commission_status = commission_status;
    }


    public Annonce getIdAnnonce() {
        return idAnnonce;
    }


    public void setIdAnnonce(Annonce idAnnonce) {
        this.idAnnonce = idAnnonce;
    }


}
