package com.c_project.auto_occasion.connexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Connexion {
    public Connection getConnection() throws Exception {
        String driver = "org.postgresql.Driver";
        //String url = "jdbc:postgresql://localhost:5432/vamoccaz";
        String url = "jdbc:postgresql://monorail.proxy.rlwy.net:15195/railway";
        Class.forName(driver);
        //Connection connect = DriverManager.getConnection(url, "postgres", "admin");
        Connection connect = DriverManager.getConnection(url, "postgres", "B1-gagCe126F-ef4fa4G6F-*5Ce-Dc1B");
        return connect;
    }
}