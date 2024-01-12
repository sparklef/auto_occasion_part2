package com.c_project.auto_occasion.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Service;

import com.c_project.auto_occasion.connexion.Connexion;

@Service
public class Utilisateur_siteService {

    public Utilisateur_siteService() {
    }
      // crud
      public void create(Connection con, String email,String nom,String prenom,String mdp,String contact) throws Exception {
        Statement stmt = null;
        try{
            String query = "INSERT INTO utilisateur_site(email,nom,prenom,mdp,contact) VALUES('"+ email +"','"+ nom +"','"+ prenom +"','"+ mdp +"','"+ contact +"')";
            stmt = con.createStatement();
            System.out.println("Saving "+ email +", "+ nom +","+ prenom +","+ mdp +","+ contact +" in the table utilisateur_site");
            System.out.println(query);
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Error while saving "+ email +", "+ nom +","+ prenom +","+ mdp +","+ contact +" in utilisateur_site");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
    public void create(String email,String nom,String prenom,String mdp,String contact) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, email,nom,prenom,mdp,contact);
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with insertion in utilisateur_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
    }

    public void verificationUser(Connection con, String email, String password) throws SQLException, Exception {
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM utilisateur_site WHERE email = ? AND mdp = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new Exception("User does not exist");
            }
        } catch (SQLException e) {
            System.out.println("Error while verifying "+ email +", "+ password +" in utilisateur_site");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
      }
      
     
    
      public void verificationUser(String email, String password) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            try {
                verificationUser(con, email,password);
                System.out.println("User verified successfully.");
            } catch (Exception e) {
                System.out.println("User does not exist.");
                throw e;
            }
            con.commit();
        }catch (SQLException e) {
            System.out.println("Error persist with verification in utilisateur_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
     }
     
}
