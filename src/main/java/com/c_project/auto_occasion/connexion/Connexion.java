package com.c_project.auto_occasion.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    public Connection getConnection() throws Exception {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/vamoccaz";
        //String url = "jdbc:postgresql://monorail.proxy.rlwy.net:13606/railway";
        Class.forName(driver);
        //Connection connect = DriverManager.getConnection(url, "postgres", "ED1-d4bc-dcE6c524bd*DEgfab65fcfB");
        try ( Connection connect = DriverManager.getConnection(url, "postgres", "admin") ) {
            return connect;
        }
    }
}
