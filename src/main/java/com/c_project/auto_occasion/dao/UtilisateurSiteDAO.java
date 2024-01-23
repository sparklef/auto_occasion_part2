package com.c_project.auto_occasion.dao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.UUID;

import com.c_project.auto_occasion.connexion.Connexion;
import com.c_project.auto_occasion.model.Admin_site;
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
           ResultSet rs = null;
           String token = null;
           try {
               String query = "INSERT INTO utilisateur_site(email, nom, prenom, mdp, contact) VALUES(?, ?, ?, ?, ?) RETURNING iduser";
               pstmt = con.prepareStatement(query);
               pstmt.setString(1, newUser.getEmail());
               pstmt.setString(2, newUser.getNom());
               pstmt.setString(3, newUser.getPrenom());
               pstmt.setString(4, newUser.getMdp());
               pstmt.setString(5, newUser.getContact());
               System.out.println("Saving " + newUser.getEmail() + " in the table utilisateur_site");
               System.out.println(query);
               pstmt.executeQuery();

               // Close the PreparedStatement before using it to get generated keys
               pstmt.close();

               query = "SELECT iduser FROM utilisateur_site WHERE email = ? AND mdp = ?";
               pstmt = con.prepareStatement(query);
               pstmt.setString(1, newUser.getEmail());
               pstmt.setString(2, newUser.getMdp());
               rs = pstmt.executeQuery();

               if (rs.next()) {
                   int userId = rs.getInt(1);
                   token = generateToken(newUser.getEmail(), newUser.getMdp());
                   saveTokenToDatabase(con, token, userId);
                   System.out.println("Generated user ID: " + userId);
               }
           } catch (SQLException e) {
               System.out.println("Error while saving " + newUser.getEmail() + " in utilisateur_site");
               throw e;
           } finally {
               try {
                   if (rs != null) {
                       rs.close();
                   }
               } catch (SQLException e) {
                   System.out.println("Error closing ResultSet: " + e.getMessage());
               }

               try {
                   if (pstmt != null) {
                       pstmt.close();
                   }
               } catch (SQLException e) {
                   System.out.println("Error closing PreparedStatement: " + e.getMessage());
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
    private static final long TOKEN_EXPIRATION_TIME = 1800000; // 1 hour in milliseconds
    public String verificationUser(Connection con, String email, String password) throws SQLException, Exception {
        Statement stmt = null;
        String token = null;
        try {
            String sql = "SELECT * FROM utilisateur_site WHERE email = '" + email + "' AND mdp = '" + password +"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            if (!rs.next()) {
                throw new Exception("User does not exist" + email + password);
            } else {
                if (email.equals(rs.getString("email")) && password.equals(rs.getString("mdp"))) {
                    System.out.println("Email and password are correct" + email + "password" + password);
                    int userId = getIdUser(con, email, password);
                    token = generateToken(email, password);
                    saveTokenToDatabase(con, token, userId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while verifying " + email + ", " + password + " in utilisateur_site");
            throw e;
        } finally {
            if(stmt != null) {
                stmt.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return token;
    }
    /// generation token
    public String generateBearerToken(String email, String password) {
        Utilisateur_site user = new Utilisateur_site();
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Date expirationDate = new Date(System.currentTimeMillis() + 1800000);
        String token = Jwts.builder()
                .setSubject(user.getNom())
                .claim("iduser", user.getIdUser())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }
    private String generateToken(String email, String password) {
        // Combine email, password, current timestamp, expiration time, and some additional information before hashing
        long currentTimestamp = System.currentTimeMillis();
        long expirationTimestamp = currentTimestamp + TOKEN_EXPIRATION_TIME;
        String combinedInfo = email + password + currentTimestamp + expirationTimestamp + "YourSecretSalt"; // Add a secret salt for security

        // Hash the combined information using a secure hashing algorithm (e.g., SHA-256)
        String hashedInfo = sha256Hash(combinedInfo);

        // Combine hashed information, timestamp, and expiration time to create the final token
        return hashedInfo + "-" + currentTimestamp + "-" + expirationTimestamp;
    }

    public boolean verifyToken(String token) {
        // Extract timestamp and expiration time from the token
        String[] parts = token.split("-");
        if (parts.length != 3) {
            return false; // Invalid token format
        }

        String hashedInfo = parts[0];
        long timestamp = Long.parseLong(parts[1]);
        long expirationTimestamp = Long.parseLong(parts[2]);

        // Check token expiration
        if (System.currentTimeMillis() > expirationTimestamp) {
            return false; // Token has expired
        }

        return true;
    }

    private String sha256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());

            // Convert byte array to a hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }
    /// maka utilisateur ray
    public Utilisateur_site findOne(Connection con, int id_user) throws Exception {
        Utilisateur_site user = new Utilisateur_site();
        Statement stmt = null;
        ResultSet res=null;
        try {
            String query = "SELECT * FROM utilisateur_site WHERE iduser = " + id_user;
            stmt = con.createStatement();
            res=stmt.executeQuery(query);
            while (res.next()) {
                user=new Utilisateur_site(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("Error while finding the user with the id " + id_user + " in utilisateur_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
            if(res != null) {
                res.close();
            }
        }
        return user;
    }
    public Utilisateur_site findOne(int id_user) throws Exception {
        Connexion c = new Connexion();
        Connection con = null;
        Utilisateur_site user = new Utilisateur_site();
        try{
            con = c.getConnection();
            user = findOne(con, id_user);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
        }
        return user;
    }
    private int getIdUser(Connection con, String email, String password) throws SQLException {
        Statement stmt = null;
        ResultSet res=null;
        int userId = 0;
        try {
            String query = "SELECT iduser FROM utilisateur_site WHERE email = '" + email + "' AND mdp = '" + password +"'";
            stmt = con.createStatement();
            res=stmt.executeQuery(query);
            while (res.next()) {
                userId=res.getInt("idUser");
            }
        } catch (SQLException e) {
            System.out.println("Error while finding the user with the email " + email + " in utilisateur_site");
            throw e;
        } finally {
            if(con != null) {
                con.close();
            }
            if(res != null) {
                res.close();
            }
        }
        return userId;
    }
    private void saveTokenToDatabase(Connection con, String token, int userId) throws SQLException, Exception {
        Connexion c = new Connexion();
        con = c.getConnection();
        // Insert the token information into the utilisateur_token table
        String insertQuery = "INSERT INTO utilisateur_token (token_user, idUser, date_exp) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, userId);

            // Set expiration date
            long expirationTimestamp = System.currentTimeMillis() + TOKEN_EXPIRATION_TIME;
            preparedStatement.setDate(3, new java.sql.Date(expirationTimestamp));

            preparedStatement.executeUpdate();
        }
    }
}
