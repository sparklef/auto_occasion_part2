package com.c_project.auto_occasion.connexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Connexion {
    public Connection getConnection() throws Exception {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/vamoccaz";
        Class.forName(driver);
        Connection connect = DriverManager.getConnection(url, "postgres", "admin");
        return connect;
    }
}