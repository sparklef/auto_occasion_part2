package com.c_project.auto_occasion.services;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.dao.UtilisateurSiteDAO;
import com.c_project.auto_occasion.model.Utilisateur_site;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class Utilisateur_siteService {
    private UtilisateurSiteDAO utilisateurSiteDAO;
    private Connexion con;
   
    
      public Utilisateur_siteService() {
        utilisateurSiteDAO = new UtilisateurSiteDAO();
        con = new Connexion();
    }
    // crud

    public void create(Utilisateur_site newUtilisateur) throws Exception {
        try {
            utilisateurSiteDAO.create(newUtilisateur);
        } catch (SQLException e) {
            throw e;
        }
    }
   
      public void verificationUser(String email, String password) throws Exception {
        Connection connex = null;
        try{
            connex = con.getConnection();
            try {
               utilisateurSiteDAO.verificationUser(email, password);
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

     public Utilisateur_site findToken(String token) throws Exception {
        Connection connection = null;
        Utilisateur_site one_fav = new Utilisateur_site();
        try {
            connection = con.getConnection();
            one_fav = utilisateurSiteDAO.findTokenUser(token);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
        }
        return one_fav;
    }
}
