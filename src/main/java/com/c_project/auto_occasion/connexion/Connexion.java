package com.c_project.auto_occasion.connexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Connexion {
    public Connection getConnection() throws Exception {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/vamoccaz";
        //String url = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:6543/postgres";
        Class.forName(driver);
        Connection connect = DriverManager.getConnection(url, "postgres", "admin");
        //Connection connect = DriverManager.getConnection(url, "postgres.dugzlausumrdqzrlxlwr", "RgYsXaaTxPpRsq7h");
        return connect;
    }
}