package com.c_project.auto_occasion.dao;
import java.sql.*;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Utilisateur_site;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class UtilisateurSiteDAO {

    public UtilisateurSiteDAO() {
    }
       // crud
    public void create(Connection con, Utilisateur_site newUser) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String query = "INSERT INTO admin_site(email, nom, prenom, mdp, contact) VALUES(?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newUser.getEmail());
            pstmt.setString(2, newUser.getNom());
            pstmt.setString(3, newUser.getPrenom());
            pstmt.setString(4, newUser.getMdp());
            pstmt.setString(5, newUser.getContact());
            System.out.println("Saving " + newUser.getEmail() + " in the table utilisateur_site");
            System.out.println(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while saving " + newUser.getEmail() + " in utilisateur_site");
            throw e;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    public void create(Utilisateur_site newUser) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        try{
            con = c.getConnection();
            con.setAutoCommit(false);
            create(con, newUser);
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
        Statement stmt = null;
        try {
            String sql = "SELECT * FROM utilisateur_site WHERE email = '" + email + "' AND mdp = '" + password + "'";

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            if (!rs.next()) {
                throw new Exception("User does not exist" + email + password);
            } else {
                if (rs.getString("email") == email && rs.getString("mdp") == password) {
                    System.out.println("Email and password are correct" + email + "password" + password);   
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while verifying " + email + ", " + password + " in utilisateur_site");
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
                System.out.println("Diso oooot.");
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
     /// generation token
    public String generateBearerToken() {
        Utilisateur_site user = new Utilisateur_site();
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.ES256);
        Date expirationDate = new Date(System.currentTimeMillis() + 1800000);
        String token = Jwts.builder()
                .setSubject(user.getNom())
                .claim("iduser", user.getIdUser())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .compact();
        return token;
    }
}
