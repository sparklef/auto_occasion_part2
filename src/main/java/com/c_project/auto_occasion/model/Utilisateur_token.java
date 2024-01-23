package com.c_project.auto_occasion.model;

import java.util.Date;

public class Utilisateur_token {
    int id_token;
    String user_s_token;
    int id_User;
    Date date_expiration;

    public Utilisateur_token() {
    }

    public int getId_token() {
        return id_token;
    }

    public void setId_token(int id_token) {
        this.id_token = id_token;
    }

    public String getUser_s_token() {
        return user_s_token;
    }

    public void setUser_s_token(String user_s_token) {
        this.user_s_token = user_s_token;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public Utilisateur_token(int id_token, String user_s_token, int id_User, Date date_expiration) {
        this.id_token = id_token;
        this.user_s_token = user_s_token;
        this.id_User = id_User;
        this.date_expiration = date_expiration;
    }
}
