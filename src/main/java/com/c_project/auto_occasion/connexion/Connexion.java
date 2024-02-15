package com.c_project.auto_occasion.connexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Connexion {
    public Connection getConnection() throws Exception {
        String driver = "org.postgresql.Driver";
        //String url = "jdbc:postgresql://localhost:5432/vamoccaz";
        String url = "jdbc:postgresql://monorail.proxy.rlwy.net:11909/railway";
        Class.forName(driver);
        //Connection connect = DriverManager.getConnection(url, "postgres", "admin");
        Connection connect = DriverManager.getConnection(url, "postgres", "GB1AbEGac-Gc2gDdA*EB56d2b3*aEfF6");
        return connect;
    }
}