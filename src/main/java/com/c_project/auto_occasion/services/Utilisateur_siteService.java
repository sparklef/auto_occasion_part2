package com.c_project.auto_occasion.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.UtilisateurSiteDAO;

@Service
public class Utilisateur_siteService {
    private UtilisateurSiteDAO utilisateurSiteDAO;
    private Connexion con;
   
    
      public Utilisateur_siteService() {
        utilisateurSiteDAO = new UtilisateurSiteDAO();
        con = new Connexion();
    }
    // crud
    
    public void create(String email,String nom,String prenom,String mdp,String contact) throws Exception {
        Connection connex = null;
        try{
            connex = con.getConnection();
           utilisateurSiteDAO.create(connex, email, nom, prenom, mdp, contact);
          
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in utilisateur_site");
            throw e;
        } finally {
            if(connex != null) {
                connex.close();
            }
        }
    }


   
      public void verificationUser(String email, String password) throws Exception {
        Connection connex = null;
        try{
            connex = con.getConnection();
            try {
               utilisateurSiteDAO.verificationUser(connex, email, password);
                System.out.println("User verified successfully.");
            } catch (Exception e) {
                System.out.println("Diso oooot.");
                throw e;
            }
       
        }catch (SQLException e) {
            System.out.println("Error persist with verification in utilisateur_site");
            throw e;
        } finally {
            if(connex != null) {
                connex.close();
            }
        }
     }
     
}
